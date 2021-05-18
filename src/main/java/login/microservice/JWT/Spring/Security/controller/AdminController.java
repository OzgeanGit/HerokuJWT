package login.microservice.JWT.Spring.Security.controller;

import login.microservice.JWT.Spring.Security.config.jwt.JwtProvider;
import login.microservice.JWT.Spring.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/modify")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;


}
