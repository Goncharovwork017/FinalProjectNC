package by.goncharov.nc.entities;

import javax.persistence.*;


/**
 * Created by ivan on 01.05.2017.
 */



@Entity
@Table(name="course")
public class Course extends AbstractEntity {

    private String name;
    private Acc acc;
    private String courseDescription;
    private String status;

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", acc=" + acc +
                ", courseDescription='" + courseDescription + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Column(nullable = false, name = ("Name"))
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "UserID")
    public Acc getAcc() {
        return acc;
    }

    public void setAcc(Acc acc) {
        this.acc = acc;
    }

    @Column(nullable = false, name = ("CourseDescription"))
    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @Column(nullable = false, name = ("Status"))

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
