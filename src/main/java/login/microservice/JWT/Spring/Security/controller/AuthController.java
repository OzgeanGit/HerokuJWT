package login.microservice.JWT.Spring.Security.controller;

import login.microservice.JWT.Spring.Security.config.jwt.JwtFilter;
import login.microservice.JWT.Spring.Security.config.jwt.JwtProvider;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtFilter jwtFilter;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
       // u.setRoleEntity(registrationRequest.);
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping("/authenticate")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getId());
        return new AuthResponse(token);
    }



  /*  @GetMapping("/currentuser")
    public JSONObject getId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", jwtProvider.getLoginFromToken(jwtFilter.getTokenFromRequest((HttpServletRequest)) );

 SAU EntityResponse
        return jsonObject;
    }

   */
}
