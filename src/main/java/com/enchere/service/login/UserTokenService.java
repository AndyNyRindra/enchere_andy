package com.enchere.service.login;

import com.enchere.exception.LoginException;
import com.enchere.model.login.User;
import com.enchere.model.login.UserToken;

public interface UserTokenService {

    UserToken saveUserToken(UserToken userToken);
    UserToken getByToken(String value) throws Exception;

    void disableTokenByUser(User user) throws Exception;

    void disableTokenByToken(String value) throws Exception;
}
