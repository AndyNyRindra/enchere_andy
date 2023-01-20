package com.enchere.repo.login;

import com.enchere.model.crud.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enchere.model.login.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmailAndMdp(String email, String mdp);

    @Query(value = "SELECT user.* FROM enchere join user on user.id=enchere.id_user GROUP BY id_user,user.id order by count(enchere.id)  Limit 10", nativeQuery = true)
    public List<User> top10Populaire();

    @Query(value = "SELECT user.* FROM enchere join user on user.id=enchere.id_user GROUP BY id_user,user.id order by sum(comission)  Limit 10", nativeQuery = true)
    public List<User> top10Rentable();
}