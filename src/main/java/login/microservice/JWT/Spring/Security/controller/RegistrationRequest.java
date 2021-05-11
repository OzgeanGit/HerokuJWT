package login.microservice.JWT.Spring.Security.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

  //  @NotEmpty
   // private int role_id;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    //public String getRoleId() {
    //    return role_id;
   // }
}