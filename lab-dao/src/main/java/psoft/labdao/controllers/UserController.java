package psoft.labdao.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import psoft.labdao.entities.UserDao;
import psoft.labdao.services.JWTService;
import psoft.labdao.services.UserService;

import javax.servlet.ServletException;

@RestController
public class UserController {

    private UserService userService;
    private JWTService jwtService;

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

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDao> getUser(@PathVariable String email) {
        Optional<UserDao> user = userService.getUser(email);
        if (user.isPresent())
            return new ResponseEntity<UserDao>(user.get(), HttpStatus.OK);
        return new ResponseEntity<UserDao>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping
    public ResponseEntity<UserDao> deleteUser(@PathVariable String email, @RequestHeader("Authorization") String header) {
        if (userService.getUser(email).isEmpty()) {
            return new ResponseEntity<UserDao>(HttpStatus.NOT_FOUND);
        }
        try {
            if(jwtService.userHavePermission(header,email)){
                return new ResponseEntity<UserDao>(userService.deleteUser(email), HttpStatus.OK);
            }
        } catch (ServletException e) {
            return new ResponseEntity<UserDao>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<UserDao>(HttpStatus.UNAUTHORIZED);
    }


}