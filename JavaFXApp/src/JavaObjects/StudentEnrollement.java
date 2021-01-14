package JavaObjects;

public class StudentEnrollement {

    int studentEnrollmentId;
    StudyGroup studyGroupId;
    Student student;

    public StudentEnrollement(){}

    public StudentEnrollement(int studentEnrollmentId,StudyGroup studyGroupId, Student student){
        this.student = student;
        this.studentEnrollmentId = studentEnrollmentId;
        this.studyGroupId = studyGroupId;
    }

    public int getStudentEnrollmentId() {
        return studentEnrollmentId;
    }

    public StudyGroup getStudyGroupId() {
        return studyGroupId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudentEnrollmentId(int studentEnrollmentId) {
        this.studentEnrollmentId = studentEnrollmentId;
    }

    public void setStudyGroupId(StudyGroup studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
