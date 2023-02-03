package com.enchere.service.login;

import com.enchere.exception.LoginException;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.User;
import com.enchere.model.stat.UserPopulaire;
import com.enchere.model.stat.UserRentable;

import java.util.List;

public interface UserService {
    User login(String email, String mdp) throws LoginException;
    User findUserById(long id);

    List<UserPopulaire> top10Populaire();
    List<UserRentable> top10Rentable();

    User saveUser(User user);
    Double soldeCompte(User usr);
}
