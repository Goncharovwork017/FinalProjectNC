package web.controllers.usercontrollers;

import by.goncharov.nc.dto.dto.CourseDto;
import by.goncharov.nc.dto.dto.SheetListDto;
import by.goncharov.nc.dto.dto.UserDto;
import by.goncharov.nc.service.ICourseService;
import by.goncharov.nc.service.ISheetListService;
import by.goncharov.nc.service.IUserService;
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
@RequestMapping(value = "/student")
public class StudentController {


    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;


    @Autowired
    private ISheetListService sheetListService;

    @RequestMapping(value = "/course/all", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDto>> getAllCourse() {
        List<CourseDto> list = courseService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("id") Integer id) {
        CourseDto course = courseService.getById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @RequestMapping(value = "/sheet/read", method = RequestMethod.POST)
    public ResponseEntity createSheet(@RequestBody SheetListDto sheetListDto) {
        int id = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        sheetListDto.setUser(id);
        SheetListDto currentList = sheetListService.getSheetByCourseIdAndUserId(sheetListDto.getCourse(),id);
        CourseDto courseDto = courseService.getById(sheetListDto.getCourse());

        if(currentList == null & courseDto.getStatus().equals("STARTED")){
            sheetListService.save(sheetListDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }


    @RequestMapping(value = "/sheet/{id}", method = RequestMethod.GET)
    public ResponseEntity<SheetListDto> getSheetById(@PathVariable("id") Integer id) {
        SheetListDto sheet = sheetListService.getById(id);
        return new ResponseEntity<>(sheet, HttpStatus.OK);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUser() {
        int userID = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        UserDto user = userService.getById(userID);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable("id") Integer id,@RequestBody UserDto userDto) {
        int idUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (id == idUser) {
            userDto.setId(id);
            userService.update(userDto);
            userDto = userService.getById(userDto.getId());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/sheet/get", method = RequestMethod.GET)
    public ResponseEntity<SheetListDto> getUserSheet() {
        int userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SheetListDto sheet = sheetListService.getAllSheetByUserId(userId);
        return new ResponseEntity<>(sheet, HttpStatus.OK);
    }




}
