package lims;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {

    @FXML
    private TextField studentName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField studentID;

    @FXML
    private TextField studentEmail;
    
    @FXML
    private Button registerButton;


    @FXML
    void getLoginPage(ActionEvent event) throws Exception {
        Parent part = FXMLLoader.load(getClass().getResource("/lims/login/login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();

    }
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
    public void registerStudent(ActionEvent event) throws SQLException {
        final String username = studentName.getText().toString();
        final String user_id = studentID.getText().toString();
        final String email = studentEmail.getText().toString();
        final String password1 = password.getText().toString();
        
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "INSERT INTO users(username, user_id, email, role_id, password) VALUES('"+username+"', '"
                +user_id+"', '"+email+"', '"+1+"', '"+password1+"')";
        st.executeUpdate(query);
        conn.close();
        
          Alert a = new Alert(Alert.AlertType.INFORMATION);
          a.setContentText(username + " successfully registered!");
          a.show();
                
    }
    
    
    

}
