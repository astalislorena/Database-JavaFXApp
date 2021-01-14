package sample;

import ConnectionPackage.ConnectionJDBC;
import JavaObjects.Person;
import JavaObjects.Student;
import adminMainMenu.AdminMainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mainMenu.MainMenuController;
import professorMainMenu.ProfessorMainMenuController;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Controller {
    public TextField emailTextField;
    public PasswordField passwordTextField;
    public Button signInButton;
    public Pane loginImagePane;
    public AnchorPane anchorPane;
    public Pane loginDataPane;
    public Label loginLabel;
    public Person person;
    public Label errorMessage;

    public void signIn(ActionEvent actionEvent) throws IOException {
        // if person is a student pop up student main menu
        if (emailTextField.getText().contains("student") && this.getUser()) {
            Stage loginStage = (Stage) signInButton.getScene().getWindow();
            loginStage.hide();
            Parent root;
            try {
                FXMLLoader studentMenu = new FXMLLoader(getClass().getClassLoader().getResource("./mainMenu/FXMLMainMenu.fxml"));
                root = studentMenu.load();
                MainMenuController studentMenuController = studentMenu.getController();
                ConnectionJDBC connectionJDBC = new ConnectionJDBC();
                studentMenuController.setStudent(connectionJDBC.get_student_by_email(emailTextField.getText()));
                Stage stage = new Stage();
                stage.setTitle("Student");
                stage.setScene(new Scene(root, 1100, 900));
                stage.getIcons().add(new Image((Main.class.getResourceAsStream("../images/undraw_Graduation_ktn0.png"))));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (emailTextField.getText().contains("professor") && this.getUser()) {
            Stage loginStage = (Stage) signInButton.getScene().getWindow();
            loginStage.hide();
            Parent root;
            try {
                FXMLLoader professorMenu = new FXMLLoader(getClass().getClassLoader().getResource("./professorMainMenu/FXMLProfessorMainMenu.fxml"));
                root = professorMenu.load();
                ProfessorMainMenuController professorMenuController = professorMenu.getController();
                ConnectionJDBC connectionJDBC = new ConnectionJDBC();
                professorMenuController.setProfessor(connectionJDBC.get_professor_by_email(emailTextField.getText()));
                Stage stage = new Stage();
                stage.setTitle("Professor");
                stage.setScene(new Scene(root, 1100, 900));
                stage.getIcons().add(new Image((Main.class.getResourceAsStream("../images/undraw_Graduation_ktn0.png"))));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(emailTextField.getText().contains("admin") || emailTextField.getText().contains("superadmin") && this.getUser()) {
            Stage loginStage = (Stage) signInButton.getScene().getWindow();
            loginStage.hide();
            Parent root;
            try {
                FXMLLoader adminMenu = new FXMLLoader(getClass().getClassLoader().getResource("./adminMainMenu/FXMLAdminMainMenu.fxml"));
                root = adminMenu.load();
                AdminMainMenuController adminMenuController = adminMenu.getController();
                ConnectionJDBC connectionJDBC = new ConnectionJDBC();
                adminMenuController.setAdmin(connectionJDBC.get_admin_by_email(emailTextField.getText()));                Stage stage = new Stage();
                stage.setTitle("Admin");
                stage.setScene(new Scene(root, 1100, 900));
                stage.getIcons().add(new Image((Main.class.getResourceAsStream("../images/undraw_Graduation_ktn0.png"))));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getUser() {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        return connectionJDBC.validate_user(emailTextField.getText(), passwordTextField.getText());
    }
}
