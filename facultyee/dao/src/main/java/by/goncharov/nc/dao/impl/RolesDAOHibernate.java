package by.goncharov.nc.dao.impl;

import by.goncharov.nc.abstracts.AbstractDAO;
import by.goncharov.nc.dao.IRolesDAO;
import by.goncharov.nc.entities.Roles;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ivan on 02.06.2017.
 */
@Repository
public class RolesDAOHibernate extends AbstractDAO<Roles> implements IRolesDAO {

    private static final Logger logger = Logger.getLogger(RolesDAOHibernate.class);

    @Autowired
    public RolesDAOHibernate(SessionFactory sessionFactory) {
        super(Roles.class,sessionFactory);
    }
}
