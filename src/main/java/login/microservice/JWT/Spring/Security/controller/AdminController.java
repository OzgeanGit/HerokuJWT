package login.microservice.JWT.Spring.Security.controller;

import login.microservice.JWT.Spring.Security.config.jwt.JwtProvider;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/modify")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PutMapping("/role")
    public String updateRole(@RequestBody UpdateRoleRequest request) {
        UserEntity userEntity = userService.updateRoleUser(request.getLogin(), request.getRole());
        //find login from the request
        //find the id for the role from request
        //modify role_id  from user_table with id that was found
        //update the user with modified values
        return "Role was updated";
    }
}
