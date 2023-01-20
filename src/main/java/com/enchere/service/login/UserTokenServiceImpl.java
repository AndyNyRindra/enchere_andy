package com.enchere.service.login;

import com.enchere.exception.LoginException;
import com.enchere.exception.ResourceNotFoundException;
import com.enchere.exception.TokenNotFoundException;
import com.enchere.model.login.User;
import com.enchere.model.login.UserToken;
import com.enchere.repo.login.UserTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl implements UserTokenService{

    @Autowired
    private UserTokenRepo userTokenRepo;


    @Override
    public UserToken saveUserToken(UserToken userToken) {
        return userTokenRepo.save(userToken);
    }

    @Override
    public UserToken getByToken(String value) {
        //System.out.println("hahha1");
        if (value == null) throw new TokenNotFoundException();
        //System.out.println("hahha2");
        UserToken token = userTokenRepo.getUserTokenByToken(value);
        //System.out.println("hahha3");
        if (token != null) return token;
        else throw new TokenNotFoundException();
    }

    @Override
    public void disableTokenByUser(User user) throws Exception {
        userTokenRepo.disableTokenByUser(user.getId());
    }

    @Override
    public void disableTokenByToken(String value) throws Exception {
        userTokenRepo.disableTokenByToken(value);
    }

}
