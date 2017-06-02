package by.goncharov.nc.entities;
import javax.persistence.*;

/**
 * Created by ivan on 01.06.2017.
 */


@Entity
@Table(name="sheetlist")
public class SheetList extends AbstractEntity {




    private Acc acc;
    public Course course;
    public Integer score;
    public String shortComment;


    public SheetList() {
    }


    @Override
    public String toString() {
        return "SheetList{" +
                "acc=" + acc +
                ", course=" + course +
                ", score=" + score +
                ", shortComment='" + shortComment + '\'' +
                '}';
    }

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "UserID")
    public Acc getAcc() {
        return acc;
    }

    public void setAcc(Acc acc) {
        this.acc = acc;
    }

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "CourseID")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(nullable = false, name = ("Score"))
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(nullable = false, name = ("ShortComment"))
    public String getShortComment() {
        return shortComment;
    }

    public void setShortComment(String shortComment) {
        this.shortComment = shortComment;
    }


}
