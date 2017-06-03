package by.goncharov.nc.dto.dto;

import by.goncharov.nc.enums.RolesType;

/**
 * Created by ivan on 20.05.2017.
 */
public class RolesDTO extends AbstractDTO {

    private RolesType rolesName;

    public RolesType getRolesName() {
        return rolesName;
    }

    public void setRolesName(RolesType rolesName) {
        this.rolesName = rolesName;
    }
}
