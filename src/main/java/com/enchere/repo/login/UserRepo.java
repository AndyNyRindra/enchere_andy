package com.enchere.repo.login;

import com.enchere.model.crud.Categorie;
import com.enchere.model.stat.UserPopulaire;
import com.enchere.model.stat.UserRentable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enchere.model.login.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmailAndMdp(String email, String mdp);

    @Query(value = "SELECT \"user\".*, count(enchere.id) as stat FROM enchere join \"user\" on \"user\".id=enchere.id_user GROUP BY id_user,\"user\".id order by count(enchere.id)  desc Limit 10 ", nativeQuery = true)
    public List<UserPopulaire> top10Populaire();

    @Query(value = "SELECT * from v_user_rentable ", nativeQuery = true)
    public List<UserRentable> top10Rentable();

    @Query(value = "SELECT coalesce(sum(e.montant*i.signe),0) FROM mouvement_compte e Join type_mouvement i on e.id_type_mouvement = i.id Where e.id_user=:value", nativeQuery = true)
    public Double soldeCompte(long value);
}
