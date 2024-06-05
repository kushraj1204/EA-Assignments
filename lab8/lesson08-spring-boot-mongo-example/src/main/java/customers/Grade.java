package customers;

import org.springframework.data.mongodb.core.mapping.Document;

//@Document
public class Grade {
    private String courseName;
    private String grade;

    public Grade(String courseName, String grade) {
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
