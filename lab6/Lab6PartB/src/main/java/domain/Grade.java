package domain;

import jakarta.persistence.*;

/**
 * @author kush
 */
@Entity
public class Grade {
    @Id
    @GeneratedValue
    private Long id;
    private String grade;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    public Grade(String grade) {
        this.grade = grade;
    }

    public Grade() {

    }

    @Override
    public String toString() {
        return "Grade{" +
                "grade='" + grade + '\'' +
                ", course=" + course +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
