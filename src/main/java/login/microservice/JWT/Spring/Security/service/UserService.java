package login.microservice.JWT.Spring.Security.service;

import login.microservice.JWT.Spring.Security.entity.RoleEntity;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.repository.RoleEntityRepository;
import login.microservice.JWT.Spring.Security.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_STUDENT");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }
    public UserEntity readUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_STUDENT");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }
    public UserEntity findByLogin(String login) {

        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public UserEntity updateRoleUser(Integer id, String name, String roleName){
        UserEntity userEntity = findById(id);
        System.out.println(userEntity.getId());
        // logger.info("");
        if (userEntity != null) {
            RoleEntity userRole = roleEntityRepository.findByName("ROLE_"+roleName.toUpperCase().trim());
            System.out.println(userRole.getId()+ userRole.getName());
            userEntity.setRoleEntity(userRole);
            userEntity.setLogin(name);
            return userEntityRepository.save(userEntity);

        }
        return null;
    }

    public UserEntity findById(Integer id) {
        return userEntityRepository.findById(id).orElse(null);
    }
}
