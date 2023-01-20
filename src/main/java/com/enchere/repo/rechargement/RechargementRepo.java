package com.enchere.repo.rechargement;

import com.enchere.model.DemandeRechargement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RechargementRepo extends JpaRepository<DemandeRechargement, Long> {
    @Query(value = "SELECT * FROM demande_rechargement where status=0", nativeQuery = true)
    public List<DemandeRechargement> demandeEnCours();


    @Modifying
    @Transactional
    @Query(value = "update demande_rechargement u set status = 10 where id = :id ", nativeQuery = true)
    void refuse(long id);

    @Modifying
    @Transactional
    @Query(value = "update demande_rechargement u set status = 20 where id = :id ", nativeQuery = true)
    void accept(long id);



}
