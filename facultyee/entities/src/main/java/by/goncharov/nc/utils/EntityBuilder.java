package by.goncharov.nc.utils;


import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.entities.Roles;
import by.goncharov.nc.enums.RolesType;

/**
 * Created by ivan on 03.05.2017.
 */
public class EntityBuilder {
    private EntityBuilder(){}


    public static Acc buildUser(String login, String pass, String name, String surname, Roles roles){
        Acc acc = new Acc();
        acc.setFirstName(name);
        acc.setLastName(surname);
        acc.setLogin(login);
        acc.setPassword(pass);
        acc.setRoles(roles);
        return acc;
    }

    public static Roles buildRoles(RolesType name){
        Roles roles = new Roles();
        roles.setRolesName(name);
        return roles;
    }

}
