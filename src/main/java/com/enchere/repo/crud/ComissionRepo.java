package com.enchere.repo.crud;

import com.enchere.model.crud.Categorie;
import com.enchere.model.crud.Comission;
import com.enchere.model.login.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface ComissionRepo extends JpaRepository<Comission, Long> {
    @Modifying
    @Transactional
    @Query(value = "update comission u set valeur = :valeur, date = :date where id = :id ", nativeQuery = true)
    Comission update(double valeur, Date date, long id);


    @Query(value = "select * from comission order by date desc limit 1", nativeQuery = true)
    public Comission findLast();
}
