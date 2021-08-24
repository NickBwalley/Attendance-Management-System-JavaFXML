package lims.admin;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private Button users;

    @FXML
    private Button borrowed;

    @FXML
    void addBook(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lims/admin/AddBook.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void viewStudents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lims/admin/ViewUsers.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void viewBookStatus(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lims/students/ViewBorrowed.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}