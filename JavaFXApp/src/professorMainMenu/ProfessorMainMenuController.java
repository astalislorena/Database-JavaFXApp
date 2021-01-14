package professorMainMenu;

import ConnectionPackage.ConnectionJDBC;
import JavaObjects.Course;
import JavaObjects.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Main;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ProfessorMainMenuController {
    public Pane personalDataPane;
    public Pane defaultPane;
    public AnchorPane anchorPanePages;
    public Pane todaysActivitiesPane;
    public Pane allActivitiesPane;
    public Pane searchCoursesPane;
    public Pane studyGroupPane;
    public Pane groupMessagesPane;
    public Pane gradesPane;
    public Pane iconPane;
    public Pane scheduleAnActivityPane;
    public Pane listAllStudentsPane;
    public Pane listAllGradedGradesPane;
    public ListView tableviewTodayActivities;
    public ListView tableViewAllActivities;
    public ListView listAllGradesListView;
    public TextField emailTextField;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField cnpTextField;
    public TextField addressTextField;
    public TextField ibanTextField;
    public TextField telephoneNumberTextField;
    public TextField contactNumberTextField;
    public Pane enrollToACoursePane;
    public TextField courseNameTextField;

    public ListView searchCoursesTableView;
    public TextField courseRoleTextField;

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    private Professor professor;

    public void showPersonalData(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        personalDataPane.setVisible(true);
        emailTextField.setText(professor.getEmail());
        cnpTextField.setText(professor.getCnp());
        addressTextField.setText(professor.getAddress());
        ibanTextField.setText(professor.getIban());
        telephoneNumberTextField.setText(professor.getTelephone_number());
        contactNumberTextField.setText(professor.getContact_number());
        firstNameTextField.setText(professor.getFirstName());
        lastNameTextField.setText(professor.getLastName());
    }

    ///de modificat
    public void searchCourses(ActionEvent actionEvent) throws SQLException {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        searchCoursesPane.setVisible(true);
        for ( int i = 0; i<searchCoursesTableView.getItems().size(); i++) {
            searchCoursesTableView.getItems().clear();
        }
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        ResultSet activities = connectionJDBC.view_search_course_professor(this.professor.getCnp());
        searchCoursesTableView.getItems().add("Course Name  ");
        while(activities.next()) {
            String isIt;
            if (activities.getInt(2) == 0)
                isIt = "false";
            else isIt = "true";
            searchCoursesTableView.getItems().add(activities.getString(1) + ", ");

        }

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

    public void logout(ActionEvent actionEvent) throws IOException {
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

    public void scheduleAnActivity(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        scheduleAnActivityPane.setVisible(true);
    }

    public void showAllStudents(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        listAllStudentsPane.setVisible(true);
    }


    public void listAllGradedGrades(ActionEvent actionEvent) throws SQLException {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        listAllGradedGradesPane.setVisible(true);
        for ( int i = 0; i<listAllGradesListView.getItems().size(); i++) {
            listAllGradesListView.getItems().clear();
        }
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        ResultSet activities = connectionJDBC.view_grades(this.professor.getCnp());
        listAllGradesListView.getItems().add("CourseId ,   StudentCNP ,     Grade ");
        while(activities.next()) {
            String isIt;
            if (activities.getInt(2) == 0)
                isIt = "false";
            else isIt = "true";
            listAllGradesListView.getItems().add(activities.getInt(5) + ", "  +
                    activities.getString(3) + ", " + activities.getBigDecimal(4));

        }
    }

    ///---- Carina


    public void showTodaysActivities(ActionEvent actionEvent) throws SQLException {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        todaysActivitiesPane.setVisible(true);
        for ( int i = 0; i<tableviewTodayActivities.getItems().size(); i++) {
            tableviewTodayActivities.getItems().clear();
        }
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        ResultSet activities = connectionJDBC.view_activity_today_professor(this.professor.getCnp());
        tableviewTodayActivities.getItems().add("Activity type,     Date,      is graded?,     grade weight,       actual grade");
        while(activities.next()) {
            String isIt;
            if (activities.getInt(2) == 0)
                isIt = "false";
            else isIt = "true";
            tableviewTodayActivities.getItems().add(activities.getString(5) + "         " + activities.getDate(1) + "       " + isIt + "        " +
                    activities.getInt(3) + "        " + activities.getBigDecimal(4));

        }

    }

    public void showAllActivities(ActionEvent actionEvent) throws SQLException {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        allActivitiesPane.setVisible(true);
        for ( int i = 0; i<tableViewAllActivities.getItems().size(); i++) {
            tableViewAllActivities.getItems().clear();
        }
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        ResultSet activities = connectionJDBC.view_activity_future_professor(this.professor.getCnp());
        tableViewAllActivities.getItems().add("Activity type,     Date,      is graded?,     grade weight,       actual grade");
        while(activities.next()) {
            String isIt;
            if (activities.getInt(2) == 0)
                isIt = "false";
            else isIt = "true";
            tableViewAllActivities.getItems().add(activities.getString(5) + "         " + activities.getDate(1) + "       " + isIt + "        " +
                    activities.getInt(3) + "        " + activities.getBigDecimal(4));

        }
    }

    public void enrollProfessorCourse(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.enroll_professor_role(
                professor.getCnp(),
                courseRoleTextField.getText(),
                courseNameTextField.getText()
        );
    }


    public void downloadTodayActivitiesProfessor(ActionEvent actionEvent) {
        try {
            FileWriter myWriter = new FileWriter("TodayActivitiesProfessor.csv");
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            ResultSet activities = connectionJDBC.view_activity_today_professor(this.professor.getCnp());
            myWriter.write("Activity type, Date, is graded?, grade weight, actual grade\n");
            while(activities.next()) {
                String isIt;
                if (activities.getInt(2) == 0)
                    isIt = "false";
                else isIt = "true";
                myWriter.write(activities.getString(5) + ", " + activities.getDate(1) + ", " + isIt + ", " +
                        activities.getInt(3) + ", " + activities.getBigDecimal(4) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException | SQLException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        }

    public void downloadFutureActivitiesProfessor(ActionEvent actionEvent) {
        try {
            FileWriter myWriter = new FileWriter("FutureActivitiesProfessor.csv");
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            ResultSet activities = connectionJDBC.view_activity_future_professor(this.professor.getCnp());
            myWriter.write("Activity type, Date, is graded?, grade weight, actual grade\n");
            while(activities.next()) {
                String isIt;
                if (activities.getInt(2) == 0)
                    isIt = "false";
                else isIt = "true";
                myWriter.write(activities.getString(5) + ", " + activities.getDate(1) + ", " + isIt + ", " +
                        activities.getInt(3) + ", " + activities.getBigDecimal(4) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException | SQLException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void downloadGradeBookProfessor(ActionEvent actionEvent) {
        try {
            FileWriter myWriter = new FileWriter("GradeBookProfessor.csv");
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            ResultSet activities = connectionJDBC.view_grades(this.professor.getCnp());
            myWriter.write("CourseId  , StudentCNP   ,Grade \n");
            while(activities.next()) {
                String isIt;
                if (activities.getInt(2) == 0)
                    isIt = "false";
                else isIt = "true";
                myWriter.write(activities.getInt(5) + ", " + isIt + ", " +
                        activities.getString(3) + ", " + activities.getBigDecimal(4) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException | SQLException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void enrollToACourse(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        enrollToACoursePane.setVisible(true);
    }
    ////----
}
