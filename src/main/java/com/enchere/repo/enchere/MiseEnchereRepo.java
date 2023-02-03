package com.enchere.repo.enchere;

import com.enchere.model.enchere.MiseEnchere;
import com.enchere.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MiseEnchereRepo extends JpaRepository<MiseEnchere, Long> {

    public MiseEnchere findByIdEnchereAndEstPlusHaut(Long idEnchere, Boolean estPlusHaut);

    public List<MiseEnchere> findAllByUserAndEstPlusHaut(User user, Boolean estPlusHaut);

    public List<MiseEnchere> findAllByIdEnchere(Long idEnchere);
}
