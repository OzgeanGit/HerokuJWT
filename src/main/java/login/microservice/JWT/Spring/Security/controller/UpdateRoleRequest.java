package login.microservice.JWT.Spring.Security.controller;
import lombok.Data;
    @Data
    public class UpdateRoleRequest {

        private Integer id;
        private String name;
        private String role;


    }
