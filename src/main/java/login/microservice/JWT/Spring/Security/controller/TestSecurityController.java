package login.microservice.JWT.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @GetMapping("/admin/get")
    public String getAdmin() {
        return "Hi admin!";
    }

    @GetMapping("/student/get")
    public String getStudent() {
        return "Hi student!";
    }

    @GetMapping("/professor/get")
    public String getProfessor() {
        return "Hi Professor!";
    }
}