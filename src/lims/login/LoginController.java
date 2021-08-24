package lims.login;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import database.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lims.Information;

public class LoginController {

    @FXML
    private TextField user_id;

    @FXML
    private PasswordField user_password;

    @FXML
    private Button login_user;
    
    //DATABASE CONNECTION
    public static Connection dbConnect(){
        
        String url = "jdbc:mysql://localhost:3306/library_javafx";
        String username = "root";
        String password = "";
        
        //load drivers
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        
        //database connection
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    

    @FXML
    void loginUser(ActionEvent event) throws SQLException, IOException {
        final String userID = user_id.getText().toString();
        final String userPassword = user_password.getText().toString();
        
        if(isStudent(userID, userPassword) || isAdmin(userID, userPassword)){
            //LOAD STUDENT
            if(isStudent(userID, userPassword)){
                Parent part = FXMLLoader.load(getClass().getResource("/lims/students/Student.fxml"));
                Information.user = userID;
                Stage stage = new Stage();
                Scene scene = new Scene(part);
                stage.setScene(scene);
                stage.show();
            }
            //LOAD ADMIN
            if(isAdmin(userID, userPassword)){
                Parent part = FXMLLoader.load(getClass().getResource("/lims/admin/Admin.fxml"));
                Information.user = userID;
                Stage stage = new Stage();
                Scene scene = new Scene(part);
                stage.setScene(scene);
                stage.show();
            }
        }
        
        
        else{
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText("Incorrect Username or Password!");
            al.show();
        }
        
                
    }
    boolean isStudent(String stdID, String password) throws SQLException, IOException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT * FROM users WHERE user_id = '"+stdID+"' AND role_id = '"+1+"' AND password = '"+password+"' ";
        
        ResultSet rs = st.executeQuery(query);
        
        if(rs.next()){
//            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
//            al.setContentText("Successful Login");
//            al.show();
        conn.close();
        return true;
        }
        return false;
    }
    
    
    boolean isAdmin(String stdID1, String password2) throws SQLException, IOException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT * FROM users WHERE user_id = '"+stdID1+"' AND role_id = '"+2+"' AND password = '"+password2+"' ";
        
        ResultSet rs = st.executeQuery(query);
        
        if(rs.next()){
//            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
//            al.setContentText("Successful Login");
//            al.show();
        
        return true;
        }
        return false;
    }

}
