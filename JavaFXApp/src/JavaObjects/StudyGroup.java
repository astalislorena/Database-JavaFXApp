package JavaObjects;

public class StudyGroup {

    int studyGroupId;
    Course course;
    Professor professor;
    String name;

    public StudyGroup(){
    }

    public StudyGroup(int studyGroupId, Course course, Professor professor, String name){
    this.course = course;
    this.studyGroupId = studyGroupId;
    this.professor = professor;
    this.name = name;
    }

    public void setStudyGroupId(int studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudyGroupId() {
        return studyGroupId;
    }

    public Course getCourse() {
        return course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getName() {
        return name;
    }
}
