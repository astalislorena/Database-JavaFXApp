package JavaObjects;

public class Professor extends Person{
    String department;
    int minimum_number_of_courses;
    int maximum_number_of_courses;

    public Professor() {

    }
    public Professor(String cnp, String firstName, String lastName, String email, String password, String iban, String telephone_number, String contact_number, String address, String department, int minimum_number_of_students, int maximum_number_of_students) {
        super(cnp, firstName, lastName, email, password, iban, telephone_number, contact_number, address);
        this.department = department;
        this.minimum_number_of_courses = minimum_number_of_students;
        this.maximum_number_of_courses = maximum_number_of_students;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getMinimum_number_of_students() {
        return minimum_number_of_courses;
    }

    public void setMinimum_number_of_students(int minimum_number_of_students) {
        this.minimum_number_of_courses = minimum_number_of_students;
    }

    public int getMaximum_number_of_students() {
        return maximum_number_of_courses;
    }

    public void setMaximum_number_of_students(int maximum_number_of_students) {
        this.maximum_number_of_courses = maximum_number_of_students;
    }
}
