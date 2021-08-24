package lims.students;

import java.net.URL;
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
    private TableColumn<?, ?> book_id;

    @FXML
    private TableColumn<?, ?> book_name;

    @FXML
    private TableColumn<?, ?> book_author;
    
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
    public void fetchData() throws SQLException{
       try{
        Connection conn = ConnectDB();
        Statement st = conn.createStatement();  
        String query = "SELECT * FROM books";
        
        ResultSet rs = st.executeQuery(query);
        Book book = new Book();
        // System.out.println(rs.toString());
        if(rs.next()){
            book.book_id = rs.getString(1);
            book.book_name = rs.getString(2);
            book.book_author = rs.getString(3);
            // book.book_status = rs.getString(4);
            System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
            obList.add(book);
            conn.close();
        }
        
       } catch(SQLException e){
            e.printStackTrace();
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            fetchData();
        } catch (SQLException ex) {
            Logger.getLogger(ViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        book_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        book_author.setCellValueFactory(new PropertyValueFactory<>("book_author"));
        table.setItems(obList);
        

    }
    

}
/*private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable,String> ordernoCol;

    @FXML
    private TableColumn<ModelTable,String> custCol;

    @FXML
    private TableColumn<ModelTable,String> menuCol;

    @FXML
    private TableColumn<ModelTable,String> deliveryCol;
@Override
    public void initialize(URL url, ResourceBundle rb) {
     
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("custid"));
        menuCol.setCellValueFactory(new PropertyValueFactory<>("menuname"));
         QuantityCol.setCellValueFactory(new PropertyValueFactory<>("qnt"));
        deliveryCol.setCellValueFactory(new PropertyValueFactory<>("deliverytyp"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
       
        tableConnection();
        table.setItems(obList);
        table.refresh();
        table.getSelectionModel().clearSelection();
       
        
    }
public void tableConnection(){
        
        try {
             
            String query="{CALL `order_list`()}";
            CallableStatement stmt = con.prepareCall(query);
            
            ResultSet rs =stmt.executeQuery(query);
            while(rs.next()){
                obList.add(new ModelTa
*/

/*private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable,String> ordernoCol;

    @FXML
    private TableColumn<ModelTable,String> custCol;

    @FXML
    private TableColumn<ModelTable,String> menuCol;

    @FXML
    private TableColumn<ModelTable,String> deliveryCol;

    @FXML
    private TableColumn<ModelTable,String> addressCol;
     @FXML
    private TableColumn<ModelTable, String> QuantityCol;
    
    Connection con;
    
    ObservableList<ModelTable> obList= FXCollections.observableArrayList();
    
    public TakeOrderController(){
        con=SqlConnection.Connector();
    }
    
    @FXML
*/