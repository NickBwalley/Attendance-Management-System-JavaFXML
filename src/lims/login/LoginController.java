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
        
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT * FROM users WHERE user_id = '"+userID+"' AND password = '"+userPassword+"' ";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setContentText("Successful Login");
            al.show();
        Parent part = FXMLLoader.load(getClass().getResource("/lims/students/Student.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
        
        }
        else{
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText("Incorrect Username or Password!");
            al.show();
        }
        conn.close();
                
    }
    boolean isUserExist(String username){
        return false;
    }

}
