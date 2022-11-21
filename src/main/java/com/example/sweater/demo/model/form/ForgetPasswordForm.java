package com.example.sweater.demo.model.form;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
//@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
public class ForgetPasswordForm {

    private String email;
}