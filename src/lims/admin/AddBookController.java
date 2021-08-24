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
import static lims.MainController.dbConnect;

public class AddBookController {

    @FXML
    private TextField book_name;

    @FXML
    private TextField book_author;

    @FXML
    private Button add_book;

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
    void addBook(ActionEvent event) throws SQLException {
        final String bookName = book_name.getText().toString();
        final String bookAuthor = book_author.getText().toString();
        
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "INSERT INTO books(book_name, book_author) VALUES('"+bookName+"', '" +bookAuthor+"')";
        st.executeUpdate(query);
        conn.close();
        
          Alert a = new Alert(Alert.AlertType.INFORMATION);
          a.setContentText(bookName + " successfully registered!");
          a.show();
        

    }

}
