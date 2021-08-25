package lims.students;

import database.ConnectDB;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lims.Book;

public class ViewBooksController implements Initializable {
    @FXML
    private TableView<Book> table;
    
    @FXML
    private TableColumn<Book, String > book_id;

    @FXML
    private TableColumn<Book, String> book_name;

    @FXML
    private TableColumn<Book, String> book_author;
    
    ObservableList<Book> obList= FXCollections.observableArrayList();
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        book_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        book_author.setCellValueFactory(new PropertyValueFactory<>("book_author"));
        table.setItems(obList);
        System.out.println(obList);
        try {
            tableConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(obList);
        table.refresh();
        table.getSelectionModel().clearSelection();

        

    }
    
    public void tableConnection()throws SQLException{
    
        try {
            Connection conn = ConnectDB();
            // String query="{CALL `books`()}";
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM books";
            ResultSet rs =stmt.executeQuery(query);
            while(rs.next()){
                obList.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3)));
                System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}