package com.example.sweater.demo.model.form;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
//@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
@ToString(exclude = {"confirmPassword"})
public class RegistrationForm {

//    private String firstName = "";
//    private String lastName = "";
//    private String email = "";
//    private String password = "";
//    private String confirmPassword = "";

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}