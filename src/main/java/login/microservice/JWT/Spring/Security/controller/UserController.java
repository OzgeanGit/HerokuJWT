package login.microservice.JWT.Spring.Security.controller;

import login.microservice.JWT.Spring.Security.config.CustomUserDetails;
import login.microservice.JWT.Spring.Security.config.CustomUserDetailsService;
import login.microservice.JWT.Spring.Security.config.jwt.JwtProvider;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.repository.UserEntityRepository;
import login.microservice.JWT.Spring.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @GetMapping("/get")
    public HashMap getCurrentUser(@RequestHeader("Authorization") String auths) {
        Integer userId = jwtProvider.getLoginFromToken(auths.substring(7));
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserById(userId);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        Map map = new HashMap();
        map.put("username", auth.getName());
        map.put("role", auth.getAuthorities());
        map.put("id", userService.findByLogin(auth.getName()).getId());
        return (HashMap) map;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> listUser(@PathVariable Integer id)
    {
        Optional<UserEntity> foundUser = userEntityRepository.findById(id);
        if (foundUser.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundUser.get());
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<UserEntity>> listAllUsers()
    {
        Iterable<UserEntity> foundTasks = userEntityRepository.findAll();
        return ResponseEntity.ok(foundTasks);
    }


    @PutMapping("/update")
    public UserEntity updateRole(@RequestBody UpdateRoleRequest request) {
        return userService.updateRoleUser(request.getId(),request.getName(), request.getRole());

        //find login from the request
        //find the id for the role from request
        //modify role_id  from user_table with id that was found
        //update the user with modified values

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Integer id) {
         userEntityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
