package by.goncharov.nc.dao.impl;

import by.goncharov.nc.abstracts.AbstractDAO;
import by.goncharov.nc.dao.IRolesDao;
import by.goncharov.nc.entities.Roles;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ivan on 02.06.2017.
 */
@Repository
public class RolesDaoHibernate extends AbstractDAO<Roles> implements IRolesDao {

    private static final Logger logger = Logger.getLogger(RolesDaoHibernate.class);

    @Autowired
    public RolesDaoHibernate(SessionFactory sessionFactory) {
        super(Roles.class,sessionFactory);
    }
}
