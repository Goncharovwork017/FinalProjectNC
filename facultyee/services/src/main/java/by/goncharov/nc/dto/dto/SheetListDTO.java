package by.goncharov.nc.dto.dto;

/**
 * Created by ivan on 20.05.2017.
 */
public class SheetListDto extends AbstractDto {


    private int user;


    public int course;


    public int score = 0;


    public String shortComment = null;

    public SheetListDto() {
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getShortComment() {
        return shortComment;
    }

    public void setShortComment(String shortComment) {
        this.shortComment = shortComment;
    }


    @Override
    public String toString() {
        return "SheetListDto{" +
                "user=" + user +
                ", course=" + course +
                ", score=" + score +
                ", shortComment='" + shortComment + '\'' +
                '}';
    }
}
