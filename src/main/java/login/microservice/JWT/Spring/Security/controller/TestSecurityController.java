package login.microservice.JWT.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/permission")
public class TestSecurityController {



    @GetMapping("/admin")
    public String getAdmin() {
        return "Hi admin!";
    }

    @GetMapping("/student")
    public String getStudent() {
        return "Hi student!";
    }

    @GetMapping("/professor")
    public String getProfessor() {
        return "Hi Professor!";
    }

}