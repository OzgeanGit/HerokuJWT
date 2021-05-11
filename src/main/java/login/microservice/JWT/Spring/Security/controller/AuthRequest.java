package login.microservice.JWT.Spring.Security.controller;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;

    /*public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }*/
}