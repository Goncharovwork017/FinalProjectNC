package by.goncharov.nc.dto.dto;

/**
 * Created by ivan on 03.06.2017.
 */
public class UserALLDTO extends AbstractDTO {


    private String firstName;
    private String lastName;
    private String login;


    public UserALLDTO() {
    }

    public UserALLDTO(int id, String firstName, String lastName, String login) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UserALLDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
