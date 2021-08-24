package lims.students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lims.Information;

public class BorrowBookController {

    @FXML
    private TextField book_id;

    @FXML
    private Button borrow_book;
    
    @FXML
    void studentBorrowBook(ActionEvent event) throws SQLException{     
        getBook();
    }
    
    public void getBook() throws SQLException{
        final String book_id1 = book_id.getText().toString();
        Connection conn = ConnectDB();
        Statement st = conn.createStatement();
        String query = "SELECT * FROM books WHERE id = '"+book_id1+"' ";
        ResultSet rs = st.executeQuery(query);
        
        if(rs.next()){
            String book_name = rs.getString(2);
            String book_author = rs.getString(3);
            final String book_status = "borrowed";
        conn.close();
        Connection conn1 = ConnectDB();
        Statement st1 = conn1.createStatement();
        String query1 = "INSERT INTO book_status(user_id, book_name, book_author, book_status) VALUES('"+Information.user+"', '"+book_name+"', '"+book_author+"', '"+book_status+"')";
        st1.executeUpdate(query1);
        conn1.close();
        
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setContentText( Information.user + " successfully borrowed book!");
        al.show();
        
        
        }else{
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setContentText("This book is not available!");
        al.show();
        }
    }
    
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
