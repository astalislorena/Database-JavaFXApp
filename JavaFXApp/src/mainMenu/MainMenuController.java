package mainMenu;

import ConnectionPackage.ConnectionJDBC;
import JavaObjects.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MainMenuController {

    public Pane personalDataPane;
    public Pane defaultPane;
    public AnchorPane anchorPanePages;
    public Pane todaysActivitiesPane;
    public Pane allActivitiesPane;
    public Pane groupEnrollmentPane;
    public Pane searchCoursesPane;
    public Pane studyGroupPane;
    public Pane groupMessagesPane;
    public Pane gradesPane;
    public Pane iconPane;
    public ListView tableviewTodayActivities;
    public ListView tableviewAllActivities;
    public TextField emailTextField;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField cnpTextField;
    public TextField addressTextField;
    public TextField ibanTextField;
    public TextField telephoneNumberTextField;
    public TextField contactNumberTextField;
    public TableView searchCoursesTableView;
    public Pane enrollStudentAtCousePane;
    private Student student;
    public void setStudent(Student student) {
        this.student = student;
    }



    public void showPersonalData(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        personalDataPane.setVisible(true);
        emailTextField.setText(student.getEmail());
        cnpTextField.setText(student.getCnp());
        addressTextField.setText(student.getAddress());
        ibanTextField.setText(student.getIban());
        telephoneNumberTextField.setText(student.getTelephone_number());
        contactNumberTextField.setText(student.getContact_number());
        firstNameTextField.setText(student.getFirstName());
        lastNameTextField.setText(student.getLastName());

    }

    ///modificat
    public void showTodaysActivities(ActionEvent actionEvent) throws SQLException {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        todaysActivitiesPane.setVisible(true);
        for ( int i = 0; i<tableviewTodayActivities.getItems().size(); i++) {
            tableviewTodayActivities.getItems().clear();
        }
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        ResultSet activities = connectionJDBC.view_activity_today_student(this.student.getCnp());
        tableviewTodayActivities.getItems().add("Date      Time     Course name     Type of activity      Professor     is graded? ");
        while(activities.next()) {
            String isIt;
            if (activities.getInt(2) == 0)
                isIt = "false";
            else isIt = "true";
            tableviewTodayActivities.getItems().add(activities.getDate(7) +  ", " + activities.getDate(8) + ", " + activities.getInt(4) + ", " +
                    activities.getString(5) + ", " + activities.getInt(2) + ", " + isIt);

        }
    }

    ///modoficat
    public void showAllActivities(ActionEvent actionEvent) throws SQLException {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        allActivitiesPane.setVisible(true);
        for ( int i = 0; i<tableviewAllActivities.getItems().size(); i++) {
            tableviewAllActivities.getItems().clear();
        }

        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        ResultSet activities = connectionJDBC.view_activity_future_student(this.student.getCnp());
        tableviewAllActivities.getItems().add("Date      Time     Course name     Type of activity      Professor     is graded? ");
        while(activities.next()) {
            String isIt;
            if (activities.getInt(2) == 0)
                isIt = "false";
            else isIt = "true";
            tableviewAllActivities.getItems().add(activities.getDate(7) +  ", " + activities.getDate(8) + ", " + activities.getInt(4) + ", " +
                    activities.getString(5) + ", " + activities.getInt(2) + ", " + isIt);

        }
    }

    ///poate si aici
    public void enrollInAStudyGroup(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        groupEnrollmentPane.setVisible(true);
    }

    public void searchCourses(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        searchCoursesPane.setVisible(true);
    }

    public void showStudyGroups(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        studyGroupPane.setVisible(true);
    }

    public void messageGroup(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        groupMessagesPane.setVisible(true);
    }

    public void showGrades(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        gradesPane.setVisible(true);
    }

    public void logout(ActionEvent actionEvent) throws IOException{
        Stage menu = (Stage) iconPane.getScene().getWindow();
        menu.hide();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("./sample/sample.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 1191, 651));
            stage.getIcons().add(new Image((Main.class.getResourceAsStream("../images/undraw_Graduation_ktn0.png"))));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///---Carina

    public void downloadTodayActivitiesStudent(ActionEvent actionEvent) {
        try {
            FileWriter myWriter = new FileWriter("TodayActivitiesStudent.csv");
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            ResultSet activities = connectionJDBC.view_activity_today_student(this.student.getCnp());
            myWriter.write("Date, Time, Course name ,Type of activity , Professor, is graded?\n");
            while(activities.next()) {
                String isIt;
                if (activities.getInt(2) == 0)
                    isIt = "false";
                else isIt = "true";
                myWriter.write( activities.getDate(7) +  ", " + activities.getDate(8) + ", " + activities.getInt(4) + ", " +
                        activities.getString(5) + ", " + activities.getInt(2) + ", " + isIt + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException | SQLException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void downloadFutureActivitiesStudent(ActionEvent actionEvent) {
        try {
            FileWriter myWriter = new FileWriter("FutureActivitiesStudent.csv");
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            ResultSet activities = connectionJDBC.view_activity_future_student(this.student.getCnp());
            myWriter.write("Date, Time, Course name ,Type of activity , Professor, is graded?\n");
            while(activities.next()) {
                String isIt;
                if (activities.getInt(2) == 0)
                    isIt = "false";
                else isIt = "true";
                myWriter.write( activities.getDate(7) +  ", " + activities.getDate(8) + ", " + activities.getInt(4) + ", " +
                        activities.getString(5) + ", " + activities.getInt(2) + ", " + isIt + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException | SQLException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void enrollAtACourse(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        enrollStudentAtCousePane.setVisible(true);
    }
}


