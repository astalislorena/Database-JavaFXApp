package ConnectionPackage;

import JavaObjects.Admin;
import JavaObjects.Person;
import JavaObjects.Student;

public class TestConnection {
    public static void main(String[] args) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        Person person = connectionJDBC.get_person_by_name("Lorena", "Astalis");
        System.out.println(person.getFirstName());
        String department = connectionJDBC.get_professor_department("1000100100101");
        System.out.println(department);
    }
}
