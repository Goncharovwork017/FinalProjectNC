import by.goncharov.nc.dao.impl.UserDAOHibernate;
import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.enums.RolesType;
import by.goncharov.nc.utils.EntityBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by ivan on 02.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-dao-context.xml")
@Transactional(transactionManager = "transactionManager")
public class UserDAOTEST {

    @Autowired
    private UserDAOHibernate userDAOHibernate;
    private Acc expectedAcc;
    private Acc actualAcc;
    private int id;





    @Before
    public void buildEntity() throws Exception
    {
        expectedAcc = EntityBuilder.buildUser("BaHbk0","1111","Ivan","Goncharov", EntityBuilder.buildRoles(RolesType.STUDENT));
        save();
    }

    @Test
    public void testGetByLogin() throws Exception {
        expectedAcc.setId(id);
        actualAcc = userDAOHibernate.getByLogin(expectedAcc.getLogin());
        Assert.assertEquals("getByLogin() method failed: ", expectedAcc, actualAcc);
        delete();
    }





    @Test
    public void testSave() throws Exception
    {
        actualAcc = userDAOHibernate.getById(id);
        Assert.assertEquals("save() method failed: ", expectedAcc, actualAcc);
        expectedAcc.setId(id);
        delete();

    }




    @Test
    public void testIsAuthorized() throws Exception {
        expectedAcc.setId(id);
        Boolean flag = userDAOHibernate.isAuthorized(expectedAcc.getLogin(), expectedAcc.getPassword());
        System.out.println(flag);
        Assert.assertTrue("IsAuthorized() method failed: ", flag);
        delete();
    }

    @Test
    public void testGetAll() throws Exception {
        for(Acc acc : userDAOHibernate.getAll())
            System.out.println(acc);
    }

    @Test
    public void testGetById() throws Exception {
        save();
        expectedAcc.setId(id);
        actualAcc = userDAOHibernate.getById(id);
        Assert.assertEquals("getById() method failed: ", expectedAcc, actualAcc);

        delete();
    }

    @Test
    public void testUpdate() throws Exception {
        expectedAcc.setId(id);
        expectedAcc.setLogin("update");
        userDAOHibernate.update(expectedAcc);
        actualAcc = userDAOHibernate.getById(id);
        Assert.assertEquals("update() method failed: ", expectedAcc, actualAcc);
        delete();
    }

    @Test
    public void testDelete() throws Exception {
        delete();
        actualAcc = userDAOHibernate.getById(id);
        Assert.assertNull("delete() method failed: ", actualAcc);
    }

    @After
    public void commitReset() throws Exception{
        expectedAcc = null;
        actualAcc = null;
        id = 0;
    }



    private void save() throws Exception {
        id = userDAOHibernate.save(expectedAcc);
    }

    private void delete() throws Exception {
        userDAOHibernate.delete(id);
    }
}


