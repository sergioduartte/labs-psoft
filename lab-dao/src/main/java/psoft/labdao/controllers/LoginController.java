package psoft.labdao.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psoft.labdao.entities.UserDao;
import psoft.labdao.services.JWTService;
import psoft.labdao.services.UserService;

import javax.servlet.ServletException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private UserService userService;
    private JWTService jwtService;

    public LoginController(UserService userService, JWTService jwtService) {
        super();
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody UserDao user) throws ServletException {
        Optional<UserDao> authUser= userService.getUser(user.getEmail());

        if(authUser.isEmpty()) {
            throw new ServletException("User not found!");
        }

        checkPassword(user,authUser);

        String token = jwtService.generateToken(authUser.get().getEmail());

        return new LoginResponse(token);
    }

    private void checkPassword(UserDao user, Optional<UserDao> authUser) throws ServletException {
        if (!authUser.get().getPassword().equals(user.getPassword())) {
            throw new ServletException("Invalid Password!");
        }
    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }


    }


}
