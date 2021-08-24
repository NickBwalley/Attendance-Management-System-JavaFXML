package lims.admin;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class ViewBorrowedController {

    @FXML
    private Button clear_student;

    @FXML
    private TableColumn<?, ?> student_id;

    @FXML
    private TableColumn<?, ?> book_name;

    @FXML
    private TableColumn<?, ?> book_author;

    @FXML
    private TableColumn<?, ?> book_status;

    @FXML
    void clearStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lims/admin/ClearStudent.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}