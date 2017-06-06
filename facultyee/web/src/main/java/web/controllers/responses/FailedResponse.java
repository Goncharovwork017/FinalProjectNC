package web.controllers.responses;



/**
 * Created by ivan on 02.06.2017.
 */


public class FailedResponse {

    private String message;
    private String status;

    public FailedResponse() {
    }

    public FailedResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String massage) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
