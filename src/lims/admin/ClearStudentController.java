package lims.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lims.Information;

public class ClearStudentController {

    @FXML
    private TextField student_id;

    @FXML
    private TextField book_id;

    @FXML
    private Button clear_student;
    
    @FXML
    void clearStudent(ActionEvent event) throws SQLException{
        final String student_id1 = student_id.getText().toString();
        final String book_id1 = book_id.getText().toString();
        final String returned_book = "returned";
        
        Connection conn = ConnectDB();
        Statement st = conn.createStatement();
        String query = "UPDATE book_status SET book_status = '"+returned_book+"' WHERE user_id = '"+student_id1+"'"
                + " AND book_id = '"+book_id1+"'";
        st.executeUpdate(query);
        conn.close();
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setContentText( student_id1 + " successfully cleared!");
        al.show();
    }
    
    //DATABASE CONNECTION
    public static Connection ConnectDB(){
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

}