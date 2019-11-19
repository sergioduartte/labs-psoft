package psoft.labdao.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psoft.labdao.entities.UserDao;
import psoft.labdao.services.UserService;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDao>> getUser() {
        return new ResponseEntity<List<UserDao>>(userService.getUser(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDao> addUser(@RequestBody UserDao user) {
        return new ResponseEntity<UserDao>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDao> getUser(@PathVariable Long id) {
        Optional<UserDao> user = userService.getUser(id);
        if (user.isPresent())
            return new ResponseEntity<UserDao>(user.get(), HttpStatus.OK);
        return new ResponseEntity<UserDao>(HttpStatus.NOT_FOUND);
    }


}