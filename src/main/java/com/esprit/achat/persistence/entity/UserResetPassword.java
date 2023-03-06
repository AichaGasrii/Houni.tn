package com.esprit.achat.persistence.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResetPassword {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
