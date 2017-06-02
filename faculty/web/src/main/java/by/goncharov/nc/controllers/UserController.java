package by.goncharov.nc.controllers;

import by.goncharov.nc.dto.dto.UserDTO;
import by.goncharov.nc.services.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
@Controller
@RequestMapping("/users")
public class UserController {


    @Autowired
    private IUserService userService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> list = userService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        UserDTO user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        UserDTO user = userService.getById(id);
        if (user != null) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }




    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDTO userDto) {
        UserDTO user = userService.getByLogin(userDto.getLogin());
        if (user == null) {
            userService.save(userDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }



    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody UserDTO userDto) {
        userService.update(userDto);
        userDto = userService.getById(userDto.getId());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody UserDTO userDto) {
        boolean flag = userService.isAuthorized(userDto.getLogin(), userDto.getPassword());
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }




}
