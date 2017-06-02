package by.goncharov.nc.controllers;

import by.goncharov.nc.dto.dto.CourseDTO;
import by.goncharov.nc.services.inter.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
@RestController
@RequestMapping(value = "/course")
public class CourseController {



    @Autowired
    private ICourseService courseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getAllCourse() {
        List<CourseDTO> list = courseService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Integer id) {
        CourseDTO course = courseService.getById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Integer id) {
        CourseDTO course = courseService.getById(id);
        if (course != null) {
            courseService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }




    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO) {
            courseService.save(courseDTO);
            return new ResponseEntity<Void>(HttpStatus.OK);

    }



    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@RequestBody CourseDTO courseDTO) {
        courseService.update(courseDTO);
        courseDTO = courseService.getById(courseDTO.getId());
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }




}
