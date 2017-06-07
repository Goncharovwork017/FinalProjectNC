package web.controllers.usercontrollers;

import by.goncharov.nc.dto.dto.CourseDto;
import by.goncharov.nc.dto.dto.SheetListDto;
import by.goncharov.nc.dto.dto.UserDto;
import by.goncharov.nc.service.ICourseService;
import by.goncharov.nc.service.ISheetListService;
import by.goncharov.nc.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.security.CustomUserDetails;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final Logger logger = Logger.getLogger(AdminController.class);


    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ISheetListService sheetListService;

    //TODO - Все для управления юзерами
    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> list = userService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        UserDto user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        UserDto user = userService.getById(id);
        if (user != null) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    //dont work
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.getByLogin(userDto.getLogin());
        if (user == null) {
            userService.save(userDto);
            logger.debug("User successfully registered!");
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }



    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable("id") Integer id,@RequestBody UserDto userDto) {
        userDto.setId(id);
        userService.update(userDto);
        userDto = userService.getById(userDto.getId());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    //TODO - Все для управления курсами
    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDto>> getAllCourse() {
        List<CourseDto> list = courseService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("id") Integer id) {
        CourseDto course = courseService.getById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }


    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Integer id) {
        CourseDto course = courseService.getById(id);
        if (course != null) {
            courseService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @RequestMapping(value = "/course/add", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody CourseDto courseDto) {
        int id = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        CourseDto course = courseService.getByName(courseDto.getName());
        if(course == null){
            courseDto.setUser(id);
            courseService.save(courseDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }  else
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);

    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCourse(@PathVariable("id") Integer id,@RequestBody CourseDto courseDto) {
        courseDto.setId(id);
        courseService.update(courseDto);
        courseDto = courseService.getById(courseDto.getId());
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }


    //TODO - Все для управления листами для оценки студентов
    @RequestMapping(value = "/sheet/all", method = RequestMethod.GET)
    public ResponseEntity<List<SheetListDto>> getAllSheet() {
        List<SheetListDto> list = sheetListService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/sheet/{id}", method = RequestMethod.GET)
    public ResponseEntity<SheetListDto> getSheetById(@PathVariable("id") Integer id) {
        SheetListDto sheet = sheetListService.getById(id);
        return new ResponseEntity<>(sheet, HttpStatus.OK);
    }

    @RequestMapping(value = "/sheet/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSheetList(@PathVariable("id") Integer id) {
        SheetListDto sheet = sheetListService.getById(id);
        if (sheet != null) {
            sheetListService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @RequestMapping(value = "/sheet/mark/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateSheet(@PathVariable("id") Integer id,@RequestBody SheetListDto sheetListDto) {
        sheetListDto.setId(id);
        sheetListService.update(sheetListDto);
        sheetListDto = sheetListService.getById(sheetListDto.getId());
        return new ResponseEntity<>(sheetListDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDto>> getUserCards() {
        int userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<CourseDto> course = courseService.findUsersCourse(userId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }















}
