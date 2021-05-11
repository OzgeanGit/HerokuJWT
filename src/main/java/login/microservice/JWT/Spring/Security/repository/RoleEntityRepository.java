package login.microservice.JWT.Spring.Security.repository;

import login.microservice.JWT.Spring.Security.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByName(String name);
}