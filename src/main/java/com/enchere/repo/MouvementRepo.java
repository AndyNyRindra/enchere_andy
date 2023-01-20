package com.enchere.repo;

import com.enchere.model.DemandeRechargement;
import com.enchere.model.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementRepo extends JpaRepository<Mouvement, Long> {
}
