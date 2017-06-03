package web.controllers.usercontrollers;

import by.goncharov.nc.dto.dto.CourseDTO;
import by.goncharov.nc.dto.dto.SheetListDTO;
import by.goncharov.nc.dto.dto.UserDTO;
import by.goncharov.nc.service.ICourseService;
import by.goncharov.nc.service.ISheetListService;
import by.goncharov.nc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ISheetListService sheetListService;



    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> list = userService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        UserDTO user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        UserDTO user = userService.getById(id);
        if (user != null) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDTO userDto) {
        UserDTO user = userService.getByLogin(userDto.getLogin());
        if (user == null) {
            userService.save(userDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }



    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody UserDTO userDto) {
        userService.update(userDto);
        userDto = userService.getById(userDto.getId());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }



    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getAllCourse() {
        List<CourseDTO> list = courseService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Integer id) {
        CourseDTO course = courseService.getById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }


    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Integer id) {
        CourseDTO course = courseService.getById(id);
        if (course != null) {
            courseService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO) {
        courseService.save(courseDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/course", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@RequestBody CourseDTO courseDTO) {
        courseService.update(courseDTO);
        courseDTO = courseService.getById(courseDTO.getId());
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }



    @RequestMapping(value = "/sheet", method = RequestMethod.GET)
    public ResponseEntity<List<SheetListDTO>> getAllSheet() {
        List<SheetListDTO> list = sheetListService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/sheet/{id}", method = RequestMethod.GET)
    public ResponseEntity<SheetListDTO> getSheetById(@PathVariable("id") Integer id) {
        SheetListDTO sheet = sheetListService.getById(id);
        return new ResponseEntity<>(sheet, HttpStatus.OK);
    }


    @RequestMapping(value = "/sheet/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSheetList(@PathVariable("id") Integer id) {
        SheetListDTO sheet = sheetListService.getById(id);
        if (sheet != null) {
            sheetListService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }




    @RequestMapping(value = "/sheet", method = RequestMethod.PUT)
    public ResponseEntity updateSheetList(@RequestBody SheetListDTO sheetListDTO) {
        sheetListService.update(sheetListDTO);
        sheetListDTO = sheetListService.getById(sheetListDTO.getId());
        return new ResponseEntity<>(sheetListDTO, HttpStatus.OK);
    }





    @RequestMapping(value = "/cards/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getUsersCards(@PathVariable int userId) {
        List<CourseDTO> userCourse = courseService.findUsersCourse(userId);
        return new ResponseEntity<>(userCourse, HttpStatus.OK);
    }



    @RequestMapping(value = "/startedCourse/", method = RequestMethod.POST)
    public ResponseEntity<CourseDTO> unblockCard(@RequestBody CourseDTO courseDTO) {
        courseService.startedCourse(courseDTO);
        return new ResponseEntity<>(new CourseDTO(courseDTO.getId(), HttpStatus.OK.toString()), HttpStatus.OK);
    }



    @RequestMapping(value = "/endedCourse/", method = RequestMethod.POST)
    public ResponseEntity<CourseDTO> blockCard(@RequestBody CourseDTO courseDTO) {
        courseService.endedCourse(courseDTO);
        return new ResponseEntity<>(new CourseDTO(courseDTO.getId(), HttpStatus.OK.toString()), HttpStatus.OK);
    }















}
