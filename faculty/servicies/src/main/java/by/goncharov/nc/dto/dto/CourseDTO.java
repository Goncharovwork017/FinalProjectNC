package by.goncharov.nc.dto.dto;

/**
 * Created by ivan on 20.05.2017.
 */
public class CourseDTO extends AbstractDTO {


    private String name;
    private int user;
    private String courseDescription;
    private String status;

    public CourseDTO() {
    }

    public CourseDTO(String name, int user, String courseDescription, String status) {
        this.name = name;
        this.user = user;
        this.courseDescription = courseDescription;
        this.status = status;
    }


    public CourseDTO(int id, String name, int user, String courseDescription, String status) {
        super(id);
        this.name = name;
        this.user = user;
        this.courseDescription = courseDescription;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "CourseDTO{" +
                "name='" + name + '\'' +
                ", user=" + user +
                ", courseDescription='" + courseDescription + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
