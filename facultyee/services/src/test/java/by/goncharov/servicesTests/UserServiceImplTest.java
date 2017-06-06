//package by.goncharov.servicesTests;
//
//import by.goncharov.nc.dto.dto.UserDto;
//import by.goncharov.nc.service.IUserService;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Created by ivan on 06.06.2017.
// */
//
//
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
//@Rollback
//public class UserServiceImplTest {
//
//
//    @Autowired
//    private IUserService userService;
//
//    private UserDto actualUserDto;
//    private UserDto expectedUserDto;
//
//
//    @Before
//    public void setUp() {
//        actualUserDto = new UserDto();
//        actualUserDto.setFirstName("Ivan");
//        actualUserDto.setLastName("Goncharov");
//        actualUserDto.setLogin("ivan");
//        actualUserDto.setPassword("1234567890");
//    }
//
//    @Test
//    public void testSave() {
//        int id = userService.save(actualUserDto);
//
//        expectedUserDto = userService.getById(id);
//        Assert.assertEquals("testSave() method failed: ", actualUserDto, expectedUserDto);
//    }
//
//    @After
//    public void dropDown() {
//        actualUserDto = null;
//        expectedUserDto = null;
//    }
//
//
//}
