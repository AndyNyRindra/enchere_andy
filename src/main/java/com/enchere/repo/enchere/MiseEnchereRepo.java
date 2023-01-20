package com.enchere.repo.enchere;

import com.enchere.model.enchere.MiseEnchere;
import com.enchere.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MiseEnchereRepo extends JpaRepository<MiseEnchere, Long> {

    @Query(value = "SELECT * FROM mise_enchere where id_enchere = ?1 ORDER BY date DESC LIMIT 1", nativeQuery = true)
    public MiseEnchere findByIdEnchereOrderByDateDesc(Long idEnchere);

    public List<MiseEnchere> findAllByUserAndEstPlusHaut(User user, Boolean estPlusHaut);

    public List<MiseEnchere> findAllByIdEnchere(Long idEnchere);
}
