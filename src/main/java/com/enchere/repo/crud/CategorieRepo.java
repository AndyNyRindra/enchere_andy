package com.enchere.repo.crud;

import com.enchere.model.DemandeRechargement;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategorieRepo extends JpaRepository<Categorie, Long> {
    @Modifying
    @Transactional
    @Query(value = "update categorie u set nom = :nom where id = :id ", nativeQuery = true)
    Categorie update(String nom, long id);

    @Query(value = "SELECT categorie.* FROM enchere join categorie on categorie.id=enchere.id_categorie GROUP BY id_categorie,categorie.id order by count(enchere.id)  Limit 10", nativeQuery = true)
    public List<Categorie> top10Populaire();

    @Query(value = "SELECT categorie.* FROM enchere join categorie on categorie.id=enchere.id_categorie GROUP BY id_categorie,categorie.id order by sum(comission)  Limit 10", nativeQuery = true)
    public List<Categorie> top10Rentable();

}
