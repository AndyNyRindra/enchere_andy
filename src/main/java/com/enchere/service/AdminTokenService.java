package com.enchere.service;

import com.enchere.exception.LoginException;
import com.enchere.model.login.Admin;
import com.enchere.model.login.AdminToken;

public interface AdminTokenService {

    AdminToken saveAdminToken(AdminToken adminToken);
    AdminToken getByToken(String value) throws Exception;

    void disableTokenByAdmin(Admin admin) throws Exception;

    void disableTokenByToken(String value) throws Exception;
}
