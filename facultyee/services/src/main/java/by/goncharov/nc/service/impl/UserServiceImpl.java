package by.goncharov.nc.service.impl;

import by.goncharov.nc.constants.ServiceConstants;
import by.goncharov.nc.dao.IUserDAO;
import by.goncharov.nc.dto.converters.Converter;
import by.goncharov.nc.dto.dto.UserDTO;
import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.exceptions.DAOUnException;
import by.goncharov.nc.exceptions.ExistUserException;
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


    private IUserDAO userDAO;
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);




    @Autowired
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public UserDTO getByLogin(String login) {
        Acc user = null;
        UserDTO userDTO = null;
        try {
            user = userDAO.getByLogin(login);
            if (user != null)
                userDTO = Converter.userToUserDto(user);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return userDTO;
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
    public int registration(UserDTO userDTO) {
        Acc acc = Converter.userDtoToUser(userDTO);
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
    public List<UserDTO> getAll() {
        List<Acc> users = null;
        UserDTO userDTO = null;
        List<UserDTO> usersDto = new ArrayList<UserDTO>();
        if (logger.isDebugEnabled()) {
            logger.debug(ServiceConstants.TRANSACTION_DEBUG);
        }
        try {
            users = userDAO.getAll();
            for (Acc user : users) {
                userDTO = Converter.userToUserDto(user);
                usersDto.add(userDTO);
            }
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return usersDto;
    }

    @Override
    public int save(UserDTO userDTO) {
        Acc user = Converter.userDtoToUser(userDTO);
        int id = 0;
        try {
            //TODO проверить как работает

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
    public UserDTO getById(int id) {
        Acc user = null;
        UserDTO userDTO = null;
        try {

            user = userDAO.getById(id);
            if(user == null){
                throw new NotFoundException("User not found!");
            }
                userDTO = Converter.userToUserDto(user);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return userDTO;
    }

    @Override
    public void update(UserDTO userDTO) {
        Acc user = Converter.userDtoToUser(userDTO);
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
