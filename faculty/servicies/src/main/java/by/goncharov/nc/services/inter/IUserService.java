package by.goncharov.nc.services.inter;

import by.goncharov.nc.dto.dto.UserDTO;

/**
 * Created by ivan on 01.06.2017.
 */
public interface IUserService extends IService<UserDTO> {


    UserDTO getByLogin(String login);
    boolean isAuthorized(String login, String password);


    int registration(UserDTO userDTO);

}
