package web.controllers.usercontrollers;

import by.goncharov.nc.dto.dto.CourseDTO;
import by.goncharov.nc.dto.dto.UserDTO;
import by.goncharov.nc.service.ICourseService;
import by.goncharov.nc.service.ISheetListService;
import by.goncharov.nc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;


    @Autowired
    private ISheetListService sheetListService;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        UserDTO user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
