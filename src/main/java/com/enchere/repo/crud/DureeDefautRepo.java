package com.enchere.repo.crud;

import com.enchere.model.crud.Categorie;
import com.enchere.model.crud.DureeDefaut;
import com.enchere.model.login.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface DureeDefautRepo extends JpaRepository<DureeDefaut, Long> {
    @Modifying
    @Transactional
    @Query(value = "update Duree_Defaut u set duree = :duree, date = :date where id = :id ", nativeQuery = true)
    DureeDefaut update(double duree, Date date, long id);

    @Query(value = "select * from Duree_Defaut order by date desc limit 1", nativeQuery = true)
    public DureeDefaut findLast();
}
