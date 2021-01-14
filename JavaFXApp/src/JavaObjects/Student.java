package JavaObjects;

public class Student extends Person{
    int year_of_study;
    int number_of_courses;
    public Student() {

    }

    public Student(String cnp, String firstName, String lastName, String email, String password, String iban, String telephone_number, String contact_number, String address, int year_of_study, int number_of_courses) {
        super(cnp, firstName, lastName, email, password, iban, telephone_number, contact_number, address);
        this.year_of_study = year_of_study;
        this.number_of_courses = number_of_courses;
    }

    public int getYear_of_study() {
        return year_of_study;
    }

    public void setYear_of_study(int year_of_study) {
        this.year_of_study = year_of_study;
    }

    public int getNumber_of_courses() {
        return number_of_courses;
    }

    public void setNumber_of_courses(int number_of_courses) {
        this.number_of_courses = number_of_courses;
    }
}
