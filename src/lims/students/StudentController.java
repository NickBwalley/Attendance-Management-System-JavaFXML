package lims.students;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudentController {

    @FXML
    private Button borrowbook;

    @FXML
    private Button returnbook;
    
    @FXML
    void borrowBook(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lims/students/BorrowBook.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void returnBook(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lims/students/ViewReturned.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void viewBooks(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lims/students/ViewBooks.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
