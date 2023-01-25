package com.enchere.service.login;

import com.enchere.exception.LoginException;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.User;

import java.util.List;

public interface UserService {
    User login(String email, String mdp) throws LoginException;
    User findUserById(long id);

    List<User> top10Populaire();
    List<User> top10Rentable();

    User saveUser(User user);
    double soldeCompte(User usr);
}
