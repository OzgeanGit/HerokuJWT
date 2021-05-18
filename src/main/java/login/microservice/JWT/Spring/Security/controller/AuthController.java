package login.microservice.JWT.Spring.Security.controller;

import login.microservice.JWT.Spring.Security.config.CustomUserDetails;
import login.microservice.JWT.Spring.Security.config.CustomUserDetailsService;
import login.microservice.JWT.Spring.Security.config.jwt.JwtFilter;
import login.microservice.JWT.Spring.Security.config.jwt.JwtProvider;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.repository.UserEntityRepository;
import login.microservice.JWT.Spring.Security.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

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
    private JwtFilter jwtFilter;
    @Autowired
    private UserEntityRepository userEntityRepository;
   // @PostMapping("/register/{role}")

   /* public String registerUserWithRole(@RequestBody @Valid RegistrationRequest registrationRequest, @RequestParam RequestParam role) {
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        // u.setRoleEntity(registrationRequest.);
        userService.saveUser(u);
        UserEntity userEntity = userService.updateRoleUser(registrationRequest.getLogin(), role.toString());
        return "OK";
    }
    */
   @GetMapping(path = "/hello")
   public JSONObject sayHello() throws JSONException {
       return new JSONObject("{'aa':'bb'}");
   }
    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        if (user.getId() != null && userEntityRepository.findById(user.getId()).isPresent())
            return ResponseEntity.badRequest().build();

        UserEntity createdUser = userService.saveUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }
    @GetMapping("/getroles")
    public Collection<GrantedAuthority> getCurrentUser(@RequestHeader("Authorization") String auths) {
        Integer userId = jwtProvider.getLoginFromToken(auths.substring(7));
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserById(userId);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        return auth.getAuthorities();
    }

    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
       // u.setRoleEntity(registrationRequest.);
      // u.getRoleEntity().getName();
        userService.saveUser(u);
       return u.getRoleEntity().getName();
        //+ intoarce json cu login si role
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
