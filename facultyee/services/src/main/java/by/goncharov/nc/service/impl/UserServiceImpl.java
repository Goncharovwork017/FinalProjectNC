package by.goncharov.nc.service.impl;

import by.goncharov.nc.constants.ServiceConstants;
import by.goncharov.nc.dao.IUserDao;
import by.goncharov.nc.dto.converters.Converter;
import by.goncharov.nc.dto.dto.UserDto;
import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.exceptions.DAOUnException;
import by.goncharov.nc.exceptions.ExistUserException;
import by.goncharov.nc.exceptions.IncorrectPasswordException;
import by.goncharov.nc.exceptions.NotFoundException;
import by.goncharov.nc.service.IUserService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ivan on 02.06.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDAO;
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);




    @Autowired
    public UserServiceImpl(IUserDao userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public UserDto getByLogin(String login) {
        Acc user = null;
        UserDto userDto = null;
        try {
            user = userDAO.getByLogin(login);
            if (user == null) {
                throw new NotFoundException("User not found!");
            }else
            userDto = Converter.userToUserDto(user);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return userDto;
    }





    @Override
    public boolean isAuthorized(String login, String password) {
        boolean isAuthorized = false;

        try {

            isAuthorized = userDAO.isAuthorized(login, password);

        }
        catch (DAOUnException e) {

            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
        }
        return isAuthorized;
    }

    @Override
    public int registration(UserDto userDto) {
        Acc acc = Converter.userDtoToUser(userDto);
        int id = 0;
        try {
            id = userDAO.save(acc);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return id;
    }

    @Override
    public int login(UserDto userDto) {
        Acc user = userDAO.getByLogin(userDto.getLogin());
        if (user == null) {
            throw new NotFoundException("User not found!");
        } else if (!user.getPassword().equals(userDto.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password entered!");
        } else {
            return user.getId();
        }
    }

    @Override
    public List<UserDto> getAll() {
        List<Acc> users = null;
        UserDto userDto = null;
        List<UserDto> usersDto = new ArrayList<UserDto>();
        if (logger.isDebugEnabled()) {
            logger.debug(ServiceConstants.TRANSACTION_DEBUG);
        }
        try {
            users = userDAO.getAll();
            for (Acc user : users) {
                userDto = Converter.userToUserDto(user);
                usersDto.add(userDto);
            }
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return usersDto;
    }

    @Override
    public int save(UserDto userDto) {
        Acc user = Converter.userDtoToUser(userDto);
        int id = 0;
        try {
            Acc actuUser = userDAO.getByLogin(user.getLogin());
            if(actuUser != null) {
                throw new ExistUserException("Such user is already exists!");
            }

            id = userDAO.save(user);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return id;
    }

    @Override
    public UserDto getById(int id) {
        Acc user = null;
        UserDto userDto = null;
        try {

            user = userDAO.getById(id);
            if(user == null){
                throw new NotFoundException("User not found!");
            }
                userDto = Converter.userToUserDto(user);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return userDto;
    }

    @Override
    public void update(UserDto userDto) {
        Acc user = Converter.userDtoToUser(userDto);
        try {
            Acc actualUser = userDAO.getById(user.getId());
            if(actualUser == null){
                throw new NotFoundException("User not found!");
            }
            userDAO.update(user);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            Acc user = userDAO.getById(id);
            if(user == null){
                throw new NotFoundException("User not found!");
            }
            userDAO.delete(id);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }
}
