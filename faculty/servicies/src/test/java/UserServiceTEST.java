import by.goncharov.nc.dto.dto.UserDTO;
import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.enums.RolesType;
import by.goncharov.nc.services.inter.IUserService;
import by.goncharov.nc.utils.EntityBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 01.06.2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-services-context.xml")
public class UserServiceTEST {


    @Autowired
    private static IUserService userService;
    private Acc expectedAcc;
    private UserDTO actualAcc;
    private int id;



    @Before
    public void buildEntity() throws Exception {
        expectedAcc = EntityBuilder.buildUser("bahk", "1111", "Ivan", "Goncharov", EntityBuilder.buildRoles(RolesType.STUDENT));
        save();
    }

    @Test
    public void testGetByLogin() throws Exception {
        expectedAcc.setId(id);
        actualAcc = userService.getByLogin(expectedAcc.getLogin());
        Assert.assertEquals("getByLogin() method failed: ", expectedAcc, actualAcc);
        delete();
    }

    @Test
    public void testIsAuthorized() throws Exception {
        expectedAcc.setId(id);
        Boolean flag = userService.isAuthorized(expectedAcc.getLogin(), expectedAcc.getPassword());
        Assert.assertTrue("IsAuthorized() method failed: ", flag);
        delete();
    }

    @Test
    public void testSave() throws Exception {
        actualAcc = userService.getById(id);
        Assert.assertEquals("save() method failed: ", expectedAcc, actualAcc);
        expectedAcc.setId(id);
        delete();
    }

    @Test
    public void testGetAll() throws Exception {
        expectedAcc.setId(id);
        List<UserDTO> listAccActual = userService.getAll();
        List<Acc> listAccExpected = new ArrayList<Acc>();
        listAccExpected.add(expectedAcc);
        Assert.assertFalse("getAll() method failed", listAccActual.contains(listAccExpected));
        delete();
    }

    @Test
    public void testGetById() throws Exception {
        expectedAcc.setId(id);
        actualAcc = userService.getById(id);
        Assert.assertEquals("getById() method failed: ", expectedAcc, actualAcc);
        delete();
    }


    @Test
    public void testUpdate() throws Exception {
        expectedAcc.setId(id);
        expectedAcc.setLogin("update");
        userService.update(actualAcc);
        actualAcc = userService.getById(id);
        Assert.assertEquals("update() method failed: ", expectedAcc, actualAcc);
        delete();
    }

    @Test
    public void testDelete() throws Exception {
        delete();
        actualAcc = userService.getById(id);
        Assert.assertNull("delete() method failed: ", actualAcc);
    }

    @After
    public void commitReset() throws Exception{

        expectedAcc = null;
        actualAcc = null;
        id = 0;

    }

    private void save() throws Exception {
        id = userService.save(actualAcc);
    }

    private void delete() throws Exception {
        userService.delete(id);
    }
}