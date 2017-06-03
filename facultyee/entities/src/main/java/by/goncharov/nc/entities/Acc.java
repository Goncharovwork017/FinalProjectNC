package by.goncharov.nc.entities;


import javax.persistence.*;

/**
 * Created by ivan on 01.05.2017.
 */
@Entity
@Table(name="acc")
public class Acc extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Roles roles;

    public Acc() {
        super();
    }

    public Acc(Acc user) {
        super(user.getId());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles();

    }



    @Override
    public String toString() {
        return "Acc{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Column(nullable = false, name = ("FirstName"))
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false, name = ("LastName"))
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false, name = ("Login"))
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(nullable = false, name = ("Password"))
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "RoleID")
    public Roles getRoles() {
        return roles;
    }
    public void setRoles(Roles roles) {
        this.roles = roles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Acc acc = (Acc) o;

        if (firstName != null ? !firstName.equals(acc.firstName) : acc.firstName != null) return false;
        if (lastName != null ? !lastName.equals(acc.lastName) : acc.lastName != null) return false;
        if (login != null ? !login.equals(acc.login) : acc.login != null) return false;
        if (password != null ? !password.equals(acc.password) : acc.password != null) return false;
        return roles != null ? roles.equals(acc.roles) : acc.roles == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
