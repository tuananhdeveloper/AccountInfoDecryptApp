package com.example.accountinfodecrypt.model;

/**
 * Created by Nguyen Tuan Anh on 18/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class Password {
    private String password;
    private String confirmPassword;

    public Password(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
