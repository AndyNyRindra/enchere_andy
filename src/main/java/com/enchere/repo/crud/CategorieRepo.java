package com.enchere.repo.crud;

import com.enchere.model.DemandeRechargement;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.Admin;
import com.enchere.model.stat.CategoriePopulaire;
import com.enchere.model.stat.CategorieRentable;
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

    @Query(value = "SELECT categorie.*,count(enchere.id) stat FROM enchere join categorie on categorie.id=enchere.id_categorie GROUP BY id_categorie,categorie.id order by count(enchere.id) desc  Limit 10 ", nativeQuery = true)
    public List<CategoriePopulaire> top10Populaire();

    @Query(value = "SELECT categorie.*,sum(prix_vente * comission) stat FROM enchere join categorie on categorie.id=enchere.id_categorie where enchere.status = 10 GROUP BY id_categorie,categorie.id order by sum(prix_vente * comission)  desc Limit 10 ", nativeQuery = true)
    public List<CategorieRentable> top10Rentable();

}
