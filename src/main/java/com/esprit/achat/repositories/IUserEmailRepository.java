package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.UserMail;

public interface IUserEmailRepository {
    public void sendCodeByMail(UserMail mail);
}
