package com.enchere.repo.login;

import com.enchere.model.login.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepo extends MongoRepository<Admin, String> {
    Admin findByEmailAndMdp(String email, String mdp);
}
