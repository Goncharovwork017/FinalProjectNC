package by.goncharov.nc.dto.dto;


/**
 * Created by ivan on 20.05.2017.
 */
public class UserDto extends AbstractDto {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String roles;



    public UserDto() {
    }

    public UserDto(int id) {
        super(id);
    }



    public UserDto(UserDto user) {
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
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +

                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
