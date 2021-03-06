package com.cognizant.fse.projectmgmt.controller;

import com.cognizant.fse.projectmgmt.exception.AppException;
import com.cognizant.fse.projectmgmt.exception.UserNotFoundException;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.service.UserService;
import com.cognizant.fse.projectmgmt.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Sanjay on 10/28/2018.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/project-management")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/users", consumes = "application/json")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userService.addUpdateUser(user);
        } catch (Exception e) {
            throw new AppException();
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping(path = "/users", consumes = "application/json")
    public ResponseEntity<String> updateUser(@RequestBody User user) {

        try {
            userService.addUpdateUser(user);
        } catch (Exception e) {
            throw new AppException();
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") long userId) {

        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            throw new AppException();
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List> getUsers() {
        List<UserTbl> userList = null;
        try {
            userList = userService.getUser();
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<List>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/search/{search}")
    public ResponseEntity<List> searchUser(@PathVariable("search") String searchString) {
        List<UserTbl> userList = null;

        try {
            userList = userService.findUser(searchString);

            if (userList.size() == 0) {
                throw new UserNotFoundException(searchString);
            }
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<List>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/sort/{sortString}")
    public ResponseEntity<List> sortUser(@PathVariable("sortString") String sortString) {
        List<UserTbl> userList = null;

        try {
            userList = userService.sortUser(sortString);
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<List>(userList, HttpStatus.OK);
    }
}
