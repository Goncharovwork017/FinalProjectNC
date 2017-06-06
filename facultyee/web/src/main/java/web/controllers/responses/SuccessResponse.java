package web.controllers.responses;


/**
 * Created by ivan on 02.06.2017.
 */


public class SuccessResponse {
    private boolean flag;
    private int id;
    private String httpStatus;

    public SuccessResponse() {
    }

    public SuccessResponse(int id, String httpStatus) {
        this.id = id;
        this.httpStatus = httpStatus;
    }

    public SuccessResponse(String httpStatus) {
        this.httpStatus = httpStatus;
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


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
