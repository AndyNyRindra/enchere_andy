package com.enchere.service.login;

import com.enchere.exception.LoginException;
import com.enchere.exception.ResourceNotFoundException;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.User;
import com.enchere.repo.login.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User login(String email, String mdp) throws LoginException {
        if (userRepo.findByEmailAndMdp(email, mdp) != null) {
            return userRepo.findByEmailAndMdp(email, mdp);
        }
        throw new LoginException("Email or password is incorrect");
    }

    @Override
    public User findUserById(long id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public List<User> top10Populaire() {
        List<User> liste =  userRepo.top10Populaire();
        return liste;
    }

    @Override
    public List<User> top10Rentable() {
        List<User> liste =  userRepo.top10Populaire();
        return liste;
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public Double soldeCompte(User usr) {
        return userRepo.soldeCompte(usr.getId());
    }
}
