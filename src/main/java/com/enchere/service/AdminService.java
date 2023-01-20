package com.enchere.service;

import com.enchere.exception.LoginException;
import com.enchere.model.login.Admin;

public interface AdminService {
    Admin login(String email, String mdp) throws LoginException;
    Admin findAdminById(String id);
}
