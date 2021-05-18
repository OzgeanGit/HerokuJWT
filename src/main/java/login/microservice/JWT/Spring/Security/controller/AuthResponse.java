package login.microservice.JWT.Spring.Security.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String role;

    /*public AuthResponse(String token) {
        this.token = token;
    }*/
}