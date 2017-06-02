package by.goncharov.nc.dao.impl;

import by.goncharov.nc.abstracts.AbstractDAO;
import by.goncharov.nc.dao.inter.ISheetListDAO;
import by.goncharov.nc.entities.SheetList;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ivan on 01.06.2017.
 */

@Repository
public class SheetListDAOHibernate extends AbstractDAO<SheetList> implements ISheetListDAO {

    private static final Logger logger = Logger.getLogger(SheetListDAOHibernate.class);

    @Autowired
    public SheetListDAOHibernate(SessionFactory sessionFactory) {
        super(SheetList.class,sessionFactory);
    }




}
