package adminMainMenu;

import ConnectionPackage.ConnectionJDBC;
import JavaObjects.*;
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

import java.io.IOException;
import java.util.Objects;

public class AdminMainMenuController {
    public Pane iconPane;
    public AnchorPane anchorPanePages;
    public Pane manageStudentsPane;
    public Pane manageProfessorsPane;
    public Pane manageAdminsPane;
    public Pane manageCoursesPane;
    public Pane manageStudyGroupsPane;
    public TextField studentCNPTextField;
    public TextField studentsFirstNameTextField;
    public TextField studentLastNameTextField;
    public TextField studentIBANTextField;
    public TextField studentPasswordTextField;
    public TextField studentEmailTextField;
    public TextField studentTelephoneNumberTextField;
    public TextField studentContactNumberTextField;
    public TextField studentAddressTextField;
    public TextField professorCNPTextField;
    public TextField professorFirstNameTextField;
    public TextField professorLastNameTextField;
    public TextField professorPasswordTextField;
    public TextField professorIBANTextField;
    public TextField professorEmailTextField;
    public TextField professorTelephoneNumberTextField;
    public Button searchProfessorByCNPButton;
    public TextField professorAddressTextField;
    public TextField professorContactNumberTextField;
    public TextField professorDepartmentTextField;
    public TextField adminCNPTextField;
    public TextField adminFirstNameTextField;
    public TextField adminLastNameTextField;
    public TextField adminPasswordTextField;
    public TextField adminIBANTextField;
    public TextField adminEmailTextField;
    public TextField adminTelephoneNumberTextField;
    public TextField adminContactNumberTextField;
    public TextField adminAddressTextField;
    public CheckBox isSuperadminCheckBox;
    public Label notSuperadminTextBox;

    public int integerValue,integerId,integerCourse;
    public TextField courseNameTextField;
    public TextArea courseDescriptionTextArea;
    public TextField courseIdTextField;
    public TextField maxNrOfStudentsTextField;
    public TextField studyGroupForCourseTextField;
    public TextField doesCourseHaveStudyGroupTextField;

    private Admin admin;

    public void manageStudents(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        manageStudentsPane.setVisible(true);

    }
    public void manageProfessors(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        manageProfessorsPane.setVisible(true);
    }
    public void manageAdmins(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        manageAdminsPane.setVisible(true);
    }
    public void manageCourses(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        manageCoursesPane.setVisible(true);
    }
    public void manageStudyGroups(ActionEvent actionEvent) {
        for (Node child : anchorPanePages.getChildren()) child.setVisible(false);
        manageStudyGroupsPane.setVisible(true);
    }
    public void logout(ActionEvent actionEvent) {
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
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public void getStudentByName(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        Person person = connectionJDBC.get_person_by_name(studentsFirstNameTextField.getText(), studentLastNameTextField.getText());
        studentCNPTextField.setText(person.getCnp());
        studentEmailTextField.setText(person.getEmail());
        studentPasswordTextField.setText(person.getPassword());
        studentIBANTextField.setText(person.getIban());
        studentContactNumberTextField.setText(person.getContact_number());
        studentTelephoneNumberTextField.setText(person.getTelephone_number());
        studentAddressTextField.setText(person.getAddress());
    }
    public void clearStudentData(ActionEvent actionEvent) {
        studentCNPTextField.clear();
        studentsFirstNameTextField.clear();
        studentLastNameTextField.clear();
        studentIBANTextField.clear();
        studentPasswordTextField.clear();
        studentEmailTextField.clear();
        studentTelephoneNumberTextField.clear();
        studentContactNumberTextField.clear();
        studentAddressTextField.clear();
    }
    public void addStudent(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.add_student(studentCNPTextField.getText(),
                studentsFirstNameTextField.getText(),
                studentLastNameTextField.getText(),
                studentEmailTextField.getText(),
                studentPasswordTextField.getText(),
                studentIBANTextField.getText(),
                studentTelephoneNumberTextField.getText(),
                studentContactNumberTextField.getText(),
                studentAddressTextField.getText());
    }
    public void searchStudentByCNP(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        Person person = connectionJDBC.get_Person_by_CNP(studentCNPTextField.getText());
        studentsFirstNameTextField.setText(person.getFirstName());
        studentLastNameTextField.setText(person.getLastName());
        studentEmailTextField.setText(person.getEmail());
        studentPasswordTextField.setText(person.getPassword());
        studentIBANTextField.setText(person.getIban());
        studentContactNumberTextField.setText(person.getContact_number());
        studentTelephoneNumberTextField.setText(person.getTelephone_number());
        studentAddressTextField.setText(person.getAddress());
    }
    public void getProfessorByName(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        Person person = connectionJDBC.get_person_by_name(professorFirstNameTextField.getText(), professorLastNameTextField.getText());
        professorCNPTextField.setText(person.getCnp());
        professorEmailTextField.setText(person.getEmail());
        professorPasswordTextField.setText(person.getPassword());
        professorIBANTextField.setText(person.getIban());
        professorContactNumberTextField.setText(person.getContact_number());
        professorTelephoneNumberTextField.setText(person.getTelephone_number());
        professorAddressTextField.setText(person.getAddress());
        professorDepartmentTextField.setText(connectionJDBC.get_professor_department(person.getCnp()));
    }
    public void clearProfessorData(ActionEvent actionEvent) {
        professorCNPTextField.clear();
        professorFirstNameTextField.clear();
        professorLastNameTextField.clear();
        professorIBANTextField.clear();
        professorPasswordTextField.clear();
        professorEmailTextField.clear();
        professorTelephoneNumberTextField.clear();
        professorContactNumberTextField.clear();
        professorAddressTextField.clear();
        professorDepartmentTextField.clear();
    }
    public void searchProfessorByCNP(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        Person person = connectionJDBC.get_Person_by_CNP(professorCNPTextField.getText());
        professorFirstNameTextField.setText(person.getFirstName());
        professorLastNameTextField.setText(person.getLastName());
        professorEmailTextField.setText(person.getEmail());
        professorPasswordTextField.setText(person.getPassword());
        professorIBANTextField.setText(person.getIban());
        professorContactNumberTextField.setText(person.getContact_number());
        professorTelephoneNumberTextField.setText(person.getTelephone_number());
        professorAddressTextField.setText(person.getAddress());
        professorDepartmentTextField.setText(connectionJDBC.get_professor_department(person.getCnp()));
    }
    public void addProfessor(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.add_professor( professorCNPTextField.getText(),
                professorFirstNameTextField.getText(),
                professorLastNameTextField.getText(),
                professorEmailTextField.getText(),
                professorPasswordTextField.getText(),
                professorIBANTextField.getText(),
                professorTelephoneNumberTextField.getText(),
                professorContactNumberTextField.getText(),
                professorAddressTextField.getText(),
                professorDepartmentTextField.getText());
    }
    public void searchAdminByCNP(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        Person person = connectionJDBC.get_Person_by_CNP(adminCNPTextField.getText());
        adminFirstNameTextField.setText(person.getFirstName());
        adminLastNameTextField.setText(person.getLastName());
        adminEmailTextField.setText(person.getEmail());
        adminPasswordTextField.setText(person.getPassword());
        adminIBANTextField.setText(person.getIban());
        adminContactNumberTextField.setText(person.getContact_number());
        adminTelephoneNumberTextField.setText(person.getTelephone_number());
        adminAddressTextField.setText(person.getAddress());
        isSuperadminCheckBox.setSelected(connectionJDBC.is_admin_super(adminCNPTextField.getText()));
    }
    public void searchAdminByName(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        Person person = connectionJDBC.get_person_by_name(adminFirstNameTextField.getText(), adminLastNameTextField.getText());
        adminCNPTextField.setText(person.getCnp());
        adminEmailTextField.setText(person.getEmail());
        adminPasswordTextField.setText(person.getPassword());
        adminIBANTextField.setText(person.getIban());
        adminContactNumberTextField.setText(person.getContact_number());
        adminTelephoneNumberTextField.setText(person.getTelephone_number());
        adminAddressTextField.setText(person.getAddress());
        isSuperadminCheckBox.setSelected(connectionJDBC.is_admin_super(adminCNPTextField.getText()));
    }

    public void addAdmin(ActionEvent actionEvent) {
        if(this.admin.isSuperAdmin()) {
            ConnectionJDBC connectionJDBC = new ConnectionJDBC();
            boolean is;
            if (isSuperadminCheckBox.isSelected()) is = true;
            else is = false;
            connectionJDBC.add_admin(adminCNPTextField.getText(),
                    adminFirstNameTextField.getText(),
                    adminLastNameTextField.getText(),
                    adminEmailTextField.getText(),
                    adminPasswordTextField.getText(),
                    adminIBANTextField.getText(),
                    adminTelephoneNumberTextField.getText(),
                    adminContactNumberTextField.getText(),
                    adminAddressTextField.getText(),
                    is);
        }
        else {
            notSuperadminTextBox.setText("You are not a superadmin, you cannot perform this action!");
        }
    }
    ///---- Carina
    public void updateStudent(ActionEvent actionEvent){
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.update_student(studentCNPTextField.getText(),
                studentsFirstNameTextField.getText(),
                studentLastNameTextField.getText(),
                studentEmailTextField.getText(),
                studentPasswordTextField.getText(),
                studentIBANTextField.getText(),
                studentTelephoneNumberTextField.getText(),
                studentContactNumberTextField.getText(),
                studentAddressTextField.getText());

    }

    public void updateProfessor(ActionEvent actionEvent){
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.update_professor(studentCNPTextField.getText(),
                studentsFirstNameTextField.getText(),
                studentLastNameTextField.getText(),
                studentEmailTextField.getText(),
                studentPasswordTextField.getText(),
                studentIBANTextField.getText(),
                studentTelephoneNumberTextField.getText(),
                studentContactNumberTextField.getText(),
                studentAddressTextField.getText());

    }
    public void updateAdmin(ActionEvent actionEvent){
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.update_admin(adminCNPTextField.getText(),
                adminFirstNameTextField.getText(),
                adminLastNameTextField.getText(),
                adminEmailTextField.getText(),
                adminPasswordTextField.getText(),
                adminIBANTextField.getText(),
                adminTelephoneNumberTextField.getText(),
                adminContactNumberTextField.getText(),
                adminAddressTextField.getText());

    }
    public void deleteStudent(ActionEvent actionEvent)
    {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.delete_student(studentCNPTextField.getText());
    }

    public void deleteProfessor(ActionEvent actionEvent)
    {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.delete_professor(studentCNPTextField.getText());
    }

    public void deleteAdmin(ActionEvent actionEvent)
    {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.delete_admin(adminCNPTextField.getText());
    }


    public void addCourse(ActionEvent actionEvent){
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.add_course(courseNameTextField.getText(),
                integerValue = Integer.parseInt(maxNrOfStudentsTextField.getText()),
                courseDescriptionTextArea.getText()
        ); }


   public void searchCourseByName(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        Course course = connectionJDBC.get_course_by_name(courseNameTextField.getText());

        maxNrOfStudentsTextField.setText( String.valueOf(course.getMaximumNumberOfStudents()));
        courseDescriptionTextArea.setText(course.getDescription());
        courseIdTextField.setText( String.valueOf(course.getCourseID()));
    }

    public void deleteCourse(ActionEvent actionEvent)
    {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.delete_course(courseNameTextField.getText());
    }

    public void updateCourse(ActionEvent actionEvent){
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.update_course(
                integerId  = Integer.parseInt( courseIdTextField.getText()),
                courseNameTextField.getText(),
                integerCourse  = Integer.parseInt( maxNrOfStudentsTextField.getText()),
                courseDescriptionTextArea.getText()
        );
    }


    ////-----------

    public void deleteStudyGroup(ActionEvent actionEvent)
    {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        connectionJDBC.delete_study_group(Integer.parseInt(studyGroupForCourseTextField.getText()));
    }

    public void addStudyGroup(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
//        connectionJDBC.add_study_group(
//              integerId =  Integer.parseInt(studyGroupForCourseTextField.getText()),
//              doesCourseHaveStudyGroupTextField.getText()
//        );
    }

    public void searchStudyGroupById(ActionEvent actionEvent) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        StudyGroup studyGroup = connectionJDBC.search_study_group( Integer.parseInt(studyGroupForCourseTextField.getText()));

        doesCourseHaveStudyGroupTextField.setText(studyGroup.getName());
    }

    ////-----------

    ///----
    public void clearAdminData(ActionEvent actionEvent) {

        adminCNPTextField.clear();
        adminFirstNameTextField.clear();
        adminLastNameTextField.clear();
        adminIBANTextField.clear();
        adminPasswordTextField.clear();
        adminEmailTextField.clear();
        adminTelephoneNumberTextField.clear();
        adminContactNumberTextField.clear();
        adminAddressTextField.clear();
        isSuperadminCheckBox.setSelected(false);
    }


}
