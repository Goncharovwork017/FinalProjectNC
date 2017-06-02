package by.goncharov.nc.services.impl;

import by.goncharov.nc.constants.ServiceConstants;
import by.goncharov.nc.dao.inter.IUserDAO;
import by.goncharov.nc.dto.converters.Converter;
import by.goncharov.nc.dto.dto.UserDTO;
import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.services.inter.IUserService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 01.06.2017.
 */
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDAO userDAO;
    private final static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int save(UserDTO userDTO) {
        Acc user = Converter.userDtoToUser(userDTO);
        int id = 0;
        try {
            id = userDAO.save(user);
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
    public UserDTO getById(int id) {
        Acc user = null;
        UserDTO userDTO = null;
        try {
            user = userDAO.getById(id);
            if (user != null)
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
            userDAO.update(user);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            userDAO.delete(id);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
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
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
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
}
