package by.goncharov.nc.dto.dto;

/**
 * Created by ivan on 20.05.2017.
 */
public abstract class AbstractDTO {



    protected int id;
    private String httpStatus;
    public AbstractDTO() {
    }

    public AbstractDTO(int id, String httpStatus) {
        this.id = id;
        this.httpStatus = httpStatus;
    }

    public AbstractDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }
}
