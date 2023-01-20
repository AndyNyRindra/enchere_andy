package com.enchere.repo.login;

import com.enchere.model.login.Admin;
import com.enchere.model.login.AdminToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface AdminTokenRepo extends MongoRepository<AdminToken, String> {
    AdminToken findByValueAndDateExpirationGreaterThan(String value, Date date);

    public AdminToken findByValue(String value);

    List<AdminToken> findALlByAdminId(String adminId);
}
