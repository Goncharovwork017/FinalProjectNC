package by.goncharov.nc.entities;



import by.goncharov.nc.enums.RolesType;

import javax.persistence.*;

/**
 * Created by ivan on 01.05.2017.
 */
@Entity
@Table(name="roles")
public class Roles extends AbstractEntity {

    private RolesType rolesName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('STUDENT', 'ADMIN')", name = ("RolesName"))
    public RolesType getRolesName() {
        return rolesName;
    }

    public void setRolesName(RolesType rolesName) {
        this.rolesName = rolesName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "rolesName=" + rolesName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Roles roles = (Roles) o;

        return rolesName == roles.rolesName;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (rolesName != null ? rolesName.hashCode() : 0);
        return result;
    }
}
