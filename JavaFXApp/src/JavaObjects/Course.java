package JavaObjects;

public class Course {
    int courseID;
    String title;
    int maximumNumberOfStudents;
    String description;

    public Course() {
    }

    public Course(int courseID, String title, int maximumNumberOfStudents, String description) {
        this.courseID = courseID;
        this.title = title;
        this.maximumNumberOfStudents = maximumNumberOfStudents;
        this.description = description;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaximumNumberOfStudents() {
        return maximumNumberOfStudents;
    }

    public void setMaximumNumberOfStudents(int maximumNumberOfStudents) {
        this.maximumNumberOfStudents = maximumNumberOfStudents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
