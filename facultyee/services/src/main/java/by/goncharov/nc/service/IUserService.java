package by.goncharov.nc.service;

import by.goncharov.nc.dto.dto.UserDto;

/**
 * Created by ivan on 02.06.2017.
 */
public interface IUserService extends IService<UserDto> {

        UserDto getByLogin(String login);
        boolean isAuthorized(String login, String password);


        int registration(UserDto userDto);

        int login(UserDto userDto);

        }
