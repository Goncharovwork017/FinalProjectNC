package by.goncharov.nc.dto.dto;


/**
 * Created by ivan on 20.05.2017.
 */
public class UserDTO extends AbstractDTO {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String roles;



    public UserDTO() {
    }

    public UserDTO(int id, String httpStatus) {
        super(id,httpStatus);
    }

//    public UserDTO(int id, String firstName, String lastName, String login,  String password) {
//        super.setId(id);
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.login = login;
//        this.password = password;
//    }

    public UserDTO(UserDTO user) {
        super(user.getId());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +

                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
