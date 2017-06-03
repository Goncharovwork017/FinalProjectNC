//package by.goncharov.nc.abstracts;
//
//import org.springframework.transaction.annotation.Transactional;
//
//
//
//
///**
// * Created by ivan on 02.06.2017.
// */
//@Transactional
//public class AbstractService  {

//<T extends AbstractEntity> implements IService<T>
//    private static Logger logger = Logger.getLogger(AbstractService.class);
//    private IDAO<T> dao;
//
//    protected AbstractService(IDAO<T> dao){this.dao = dao;}
//
//
//
//
//
//
//    @Override
//    @Transactional
//    public List<T> getAll() {
//        List<T> users = null;
//        try {
//            users = dao.getAll();
//            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
//            logger.info(users);
//        }
//        catch (DAOUnException e) {
//
//            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
//            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
//        }
//        return users;
//    }
//
//
//    @Override
//    @Transactional
//    public int save(T entity) {
//        int id = 0;
//
//        try {
//
//            id = dao.save(entity);
//
//            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
//            logger.info(entity);
//        }
//        catch (DAOUnException e) {
//
//            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
//            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
//        }
//        return id;
//    }
//
//    @Override
//    @Transactional
//    public T getById(int id) {
//        T user = null;
//
//        try {
//
//            user = dao.getById(id);
//
//
//            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
//            logger.info(user);
//        }
//        catch (DAOUnException e) {
//
//            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
//            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
//        }
//        return user;
//    }
//
//    @Override
//    @Transactional
//    public T update(T entity) {
//
//        try {
//            dao.update(entity);
//
//            return entity;
//
//        }
//        catch (DAOUnException e) {
//
//            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
//            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
//        }
//    }
//
//    @Override
//    @Transactional
//    public T delete(int id) {
//
//        try {
//            T user = getById(id);
//            dao.delete(id);
//            return user;
//
//        }
//        catch (DAOUnException e) {
//
//            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
//            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
//        }
//    }

