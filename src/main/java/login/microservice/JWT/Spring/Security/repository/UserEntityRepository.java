package login.microservice.JWT.Spring.Security.repository;

import login.microservice.JWT.Spring.Security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByLogin(String login);
}