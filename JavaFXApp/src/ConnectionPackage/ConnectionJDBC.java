package ConnectionPackage;

import JavaObjects.*;

import java.sql.*;

public class ConnectionJDBC {
    String URL = "jdbc:mysql://localhost:3306/";
    String DB_NAME =  "universitydatabase";
    String USER = "root";
    String PASSWORD = "iipMda13**";

    public boolean validate_user(String email, String password) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL validate_user(?, ?, ?)}");
            cs.setString(1, email);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.execute();
            return cs.getBoolean(3);
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return true;
    }
    public Student get_student_by_email(String email) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL get_student_by_email(?)}");
            cs.setString(1, email);
            ResultSet resultSet = cs.executeQuery();
            if (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("CNP"),
                        resultSet.getString("first_name"), // First name
                        resultSet.getString("last_name"), // Last name
                        resultSet.getString("email"), // Email
                        resultSet.getString("password"), // Password
                        resultSet.getString("IBAN"), // IBAN
                        resultSet.getString("telephone_number"), // Telephone number
                        resultSet.getString("contact_number"), // Contact number
                        resultSet.getString("address"), // Address
                        resultSet.getInt("year_of_study"), // year of study
                        resultSet.getInt("number_of_courses") // Number of courses
                );
                return student;
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }
    public Admin get_admin_by_email(String email) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL get_admin_by_email(?)}");
            cs.setString(1, email);
            ResultSet resultSet = cs.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getString("CNP"),
                        resultSet.getString("first_name"), // First name
                        resultSet.getString("last_name"), // Last name
                        resultSet.getString("email"), // Email
                        resultSet.getString("password"), // Password
                        resultSet.getString("IBAN"), // IBAN
                        resultSet.getString("telephone_number"), // Telephone number
                        resultSet.getString("contact_number"), // Contact number
                        resultSet.getString("address"), // Address
                        resultSet.getBoolean("is_superadmin")
                );
                return admin;
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }
    public Professor get_professor_by_email(String email) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL get_professor_by_email(?)}");
            cs.setString(1, email);
            ResultSet resultSet = cs.executeQuery();
            if (resultSet.next()) {
                Professor professor = new Professor(
                        resultSet.getString("CNP"),
                        resultSet.getString("first_name"), // First name
                        resultSet.getString("last_name"), // Last name
                        resultSet.getString("email"), // Email
                        resultSet.getString("password"), // Password
                        resultSet.getString("IBAN"), // IBAN
                        resultSet.getString("telephone_number"), // Telephone number
                        resultSet.getString("contact_number"), // Contact number
                        resultSet.getString("address"), // Address
                        resultSet.getString("department"),
                        resultSet.getInt("min_number_of_courses"),
                        resultSet.getInt("max_number_of_courses")
                );
                return professor;
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }
    public Person get_person_by_name(String firstName, String lastName) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL search_by_name(?, ?)}");
            cs.setString(1, firstName);
            cs.setString(2, lastName);
            ResultSet resultSet = cs.executeQuery();
            if (resultSet.next()) {
                Person person = new Person(
                        resultSet.getString("CNP"),
                        resultSet.getString("first_name"), // First name
                        resultSet.getString("last_name"), // Last name
                        resultSet.getString("email"), // Email
                        resultSet.getString("password"), // Password
                        resultSet.getString("IBAN"), // IBAN
                        resultSet.getString("telephone_number"), // Telephone number
                        resultSet.getString("contact_number"), // Contact number
                        resultSet.getString("address") // Address
                );
                return person;
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }
    public Person get_Person_by_CNP(String cnp) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL search_by_cnp(?)}");
            cs.setString(1, cnp);
            ResultSet resultSet = cs.executeQuery();
            if (resultSet.next()) {
                Person person = new Person(
                        resultSet.getString("CNP"),
                        resultSet.getString("first_name"), // First name
                        resultSet.getString("last_name"), // Last name
                        resultSet.getString("email"), // Email
                        resultSet.getString("password"), // Password
                        resultSet.getString("IBAN"), // IBAN
                        resultSet.getString("telephone_number"), // Telephone number
                        resultSet.getString("contact_number"), // Contact number
                        resultSet.getString("address") // Address
                );
                return person;
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }
    public void add_student(String cnp, String firstName, String lastName, String email, String password, String iban, String telephoneNumber, String contactNumber, String address) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL add_student(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, cnp);
            cs.setString(2, firstName);
            cs.setString(3, lastName);
            cs.setString(4, email);
            cs.setString(5, password);
            cs.setString(6, iban);
            cs.setString(7, telephoneNumber);
            cs.setString(8, contactNumber);
            cs.setString(9, address);
            cs.execute();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }

    }
    public void add_professor(String cnp, String firstName, String lastName, String email, String password, String iban, String telephoneNumber, String contactNumber, String address, String department) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL add_professor(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, cnp);
            cs.setString(2, firstName);
            cs.setString(3, lastName);
            cs.setString(4, email);
            cs.setString(5, password);
            cs.setString(6, iban);
            cs.setString(7, telephoneNumber);
            cs.setString(8, contactNumber);
            cs.setString(9, address);
            cs.setString(10, department);
            cs.execute();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }

    }
    public String get_professor_department(String cnp) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL get_professor_department(?, ?)}");
            cs.setString(1, cnp);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
           return cs.getString(2);

        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }

    //CREATE PROCEDURE add_admin(admin_cnp varchar(13), admin_first_name varchar(30), admin_last_name varchar(30), admin_email varchar(30), admin_user_password varchar(20),
    // admin_iban varchar(34), admin_telephone_number varchar(10), admin_contact_number varchar(10), admin_address varchar(30), admin_is_super boolean)
    public void add_admin(String cnp, String firstName, String lastName, String email, String password, String iban, String telephoneNumber, String contactNumber, String address, boolean isSuperAdmin) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL add_admin(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, cnp);
            cs.setString(2, firstName);
            cs.setString(3, lastName);
            cs.setString(4, email);
            cs.setString(5, password);
            cs.setString(6, iban);
            cs.setString(7, telephoneNumber);
            cs.setString(8, contactNumber);
            cs.setString(9, address);
            cs.setBoolean(10, isSuperAdmin);
            cs.execute();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }
    ///---- Carina

    public void update_student(String cnp, String firstName, String lastName, String email, String password, String iban, String telephoneNumber, String contactNumber, String address)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL update_student(?,?,?,?,?,?,?,?,?)}");
            cs.setString( 1,cnp);
            cs.setString( 2,firstName);
            cs.setString( 3,lastName);
            cs.setString( 4,email);
            cs.setString( 5,password);
            cs.setString( 6,iban);
            cs.setString( 7,telephoneNumber);
            cs.setString( 8,contactNumber);
            cs.setString( 9,address);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }

    }

    public void update_professor(String cnp, String firstName, String lastName, String email, String password, String iban, String telephoneNumber, String contactNumber, String address)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL update_professor(?,?,?,?,?,?,?,?,?)}");
            cs.setString( 1,cnp);
            cs.setString( 2,firstName);
            cs.setString( 3,lastName);
            cs.setString( 4,email);
            cs.setString( 5,password);
            cs.setString( 6,iban);
            cs.setString( 7,telephoneNumber);
            cs.setString( 8,contactNumber);
            cs.setString( 9,address);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    public void update_admin(String cnp, String firstName, String lastName, String email, String password, String iban, String telephoneNumber, String contactNumber, String address)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL update_admin(?,?,?,?,?,?,?,?,?)}");
            cs.setString( 1,cnp);
            cs.setString( 2,firstName);
            cs.setString( 3,lastName);
            cs.setString( 4,email);
            cs.setString( 5,password);
            cs.setString( 6,iban);
            cs.setString( 7,telephoneNumber);
            cs.setString( 8,contactNumber);
            cs.setString( 9,address);
            cs.execute();

        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }

    }

    public void delete_student(String cnp)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL delete_student(?)}");
            cs.setString( 1,cnp);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    public void delete_professor(String cnp)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL delete_professor(?)}");
            cs.setString( 1,cnp);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }
    public void delete_admin(String cnp)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL delete_admin(?)}");
            cs.setString( 1,cnp);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }
    public boolean is_admin_super(String adminCNP) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL is_admin_superadmin(?, ?)}");
            cs.setString(1, adminCNP);
            cs.registerOutParameter(2, Types.BOOLEAN);
            cs.execute();
            return cs.getBoolean(2);
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return true;
    }


    public void add_course(String title, int max_students, String desctiption) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL add_course(?,?,?)}");
            cs.setString(1, title);
            cs.setInt(2, max_students);
            cs.setString(3, desctiption);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    public ResultSet view_activity_today_professor(String professorCNP){
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL today_activities_professor(?)}");
            cs.setString(1, professorCNP);
            return cs.executeQuery();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }

    public ResultSet view_activity_future_professor(String professorCNP){
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL future_activities_professor(?)}");
            cs.setString(1, professorCNP);
            return cs.executeQuery();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }


    public ResultSet view_activity_today_student(String studentCNP){
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL today_activities_student(?)}");
            cs.setString(1, studentCNP);
            return cs.executeQuery();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }

    public ResultSet view_activity_future_student(String studentCNP){
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL future_activities_student(?)}");
            cs.setString(1, studentCNP);
            return cs.executeQuery();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }


    public Course get_course_by_name(String name) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL search_course(?)}");
            cs.setString(1, name);
            ResultSet resultSet = cs.executeQuery();
            if (resultSet.next()) {
                Course course = new Course(
                        resultSet.getInt("course_ID"),
                        resultSet.getString("title"),
                        resultSet.getInt("max_number_of_students"),
                        resultSet.getString("description")
                );
                return course;
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }

    public void delete_course(String name)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL delete_course(?)}");
            cs.setString( 1,name);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    public void update_course(int course_ID ,String name,int max_number_of_students,String description)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL update_course(?,?,?,?)}");
            cs.setInt( 1,course_ID);
            cs.setString( 2,name);
            cs.setInt( 3,max_number_of_students);
            cs.setString( 4,description);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    ////////


    public void add_study_group(int course_ID,String name,String CNP) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL add_study_group(?,?,?)}");
            cs.setInt(1, course_ID);
            cs.setString(2, name);
            cs.setString(3, CNP);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    public void delete_study_group(int course_ID)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL delete_study_group(?)}");
            cs.setInt( 1,course_ID);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

   public StudyGroup search_study_group(int course_Id) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL search_study_group(?)}");
            cs.setInt(1, course_Id);
            ResultSet resultSet = cs.executeQuery();
            if (resultSet.next()) {
                return new StudyGroup(
                        resultSet.getInt("study_group_ID"),
                        new Course(),
                       new Professor(),
                        resultSet.getString("name")
                );
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }

    ///-------

    public ResultSet view_search_course_professor(String CNP){
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL view_search_course_professor(?)}");
            cs.setString(1, CNP);
            return cs.executeQuery();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }

    public ResultSet view_search_course_student(String CNP){
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL view_search_course_student(?)}");
            cs.setString(1, CNP);
            return cs.executeQuery();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }



    ///CREATE PROCEDURE enroll_student(cnpUser varchar(13),userFirstName varchar(30), userLastName varchar(30),course_id int)
    public void enroll_student_course(String cnpUser,String userFirstName,String userLastName,int course_id)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL enroll_student(?,?,?,?)}");
            cs.setString( 1,cnpUser);
            cs.setString( 2,userFirstName);
            cs.setString( 3,userLastName);
            cs.setInt( 4,course_id);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    ///CREATE PROCEDURE enroll_professor(userFirstName varchar(30), userLastName varchar(30),role varchar(15),course_id int)
    public void enroll_professor_role(String userFirstName,String userLastName,String role,int course_id)
    {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL enroll_professor(?,?,?,?)}");
            cs.setString( 1,userFirstName);
            cs.setString( 2,userLastName);
            cs.setString( 3,role);
            cs.setInt( 4,course_id);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    ///CREATE PROCEDURE add_student_study_group(cnpUser varchar(13),study_group_ID int)
    public void add_student_study_group(String cnpUser, int study_group_ID) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL add_student_study_group(?,?)}");
            cs.setString(1, cnpUser);
            cs.setInt(2, study_group_ID);
            cs.execute();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }

    ///CREATE PROCEDURE delete_student_study_group(cnpUser varchar(13),study_group_ID int)
    public void delete_student_study_group(String cnpUser, int study_group_ID) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL delete_student_study_group(?,?)}");
            cs.setString(1, cnpUser);
            cs.setInt(2, study_group_ID);
            c.setAutoCommit(false);
            cs.execute();
            c.commit();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }


    ///----

    ///Incepe Spac :)
    /*
     * CREATE PROCEDURE prof_new_activity(professorCNP varchar(13), courseID int, activityTYPE varchar(15),
     * professorRole varchar(15), startDATE date, endDATE date)
     */

    public void add_prof_activity(String cnp, String courseID, String activityType, String professorRole, String startDate, String endDate) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL prof_new_activity(?,?,?,?,?,?)}");
            cs.setString(1, cnp);
            cs.setString(2, courseID);
            cs.setString(3, activityType);
            cs.setString(4, professorRole);
            cs.setString(5, startDate);
            cs.setString(6, endDate);
            cs.execute();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }
    /*
     * CREATE PROCEDURE stud_new_groupActivity(studentCNP varchar(13), studygroupID int,  dateAndTime datetime, minimumParticipants tinyint,
     * expirationTime time, Duration time)
     */
    public void add_stud_groupActivity(String cnp, String studygroupID, String dateAndTime, String minimumParticipants, String expirationTime, String Duration) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL stud_new_groupActivity(?,?,?,?,?,?)}");
            cs.setString(1, cnp);
            cs.setString(2, studygroupID);
            cs.setString(3, dateAndTime);
            cs.setString(4, minimumParticipants);
            cs.setString(5, expirationTime);
            cs.setString(6, Duration);

            cs.execute();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }
    /*
     * CREATE PROCEDURE send_message(studentCNP varchar(13), studyGroupID int, textMessage varchar(100), messageTime datetime)
     */
    public void send_message(String cnp, String studygroupID, String textMessage, String messageTime) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL send_message(?,?,?,?)}");
            cs.setString(1, cnp);
            cs.setString(2, studygroupID);
            cs.setString(3, textMessage);
            cs.setString(4, messageTime);

            cs.execute();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
    }
    /**
     *CREATE PROCEDURE suggest_members (studyGroupID int)
     */
    public ResultSet suggest_members(String studyGroupID) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL suggest_members(?)}");
            cs.setString(1, studyGroupID);
            ResultSet resultSet = cs.executeQuery();
            //resultSet.last();
            //int sugestiiSize = resultSet.getRow();
            //resultSet.beforeFirst();
            int i=0;

            return resultSet;
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }
    /**
     *CREATE PROCEDURE view_grades(cnp_stud varchar(13))
     */
    public ResultSet view_grades(String studentCNP) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL view_grades(?)}");
            cs.setString(1, studentCNP);
            ResultSet resultSet = cs.executeQuery();

            return resultSet;
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }
    /**
     * CREATE PROCEDURE view_groupMembers(studyGroupID int)
     */
    public ResultSet view_GroupMembers(String studentCNP) {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL view_groupMembers(?)}");
            cs.setString(1, studentCNP);
            ResultSet resultSet = cs.executeQuery();

            return resultSet;
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }
    /**
     * CREATE VIEW groups_view AS
     */
    public ResultSet view_Groups() {
        Connection c;
        try {
            c = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            CallableStatement cs = c.prepareCall("{CALL groups_view()}");
            ResultSet resultSet = cs.executeQuery();

            return resultSet;
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + ((SQLException) e).getSQLState());
            System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
        }
        return null;
    }

}



