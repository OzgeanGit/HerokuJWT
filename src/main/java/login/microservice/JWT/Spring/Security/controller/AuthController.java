package login.microservice.JWT.Spring.Security.controller;

import login.microservice.JWT.Spring.Security.config.CustomUserDetailsService;
import login.microservice.JWT.Spring.Security.config.jwt.JwtProvider;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.repository.UserEntityRepository;
import login.microservice.JWT.Spring.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path="/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserEntityRepository userEntityRepository;



    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        if (user.getId() != null && userEntityRepository.findById(user.getId()).isPresent())
            return ResponseEntity.badRequest().build();

        UserEntity createdUser = userService.saveUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }

    @PostMapping("/authenticate")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getId());
        return new AuthResponse(token,userEntity.getRoleEntity().getName());
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
