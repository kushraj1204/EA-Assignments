package domain;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author kush
 */
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String studentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn
    List<Grade> grades;

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public Student(String name, String studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                '}';
    }
}
