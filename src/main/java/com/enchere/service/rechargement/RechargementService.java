package com.enchere.service.rechargement;

import com.enchere.model.DemandeRechargement;
import com.enchere.model.Mouvement;
import com.enchere.model.TypeMouvement;
import com.enchere.model.login.User;
import com.enchere.repo.MouvementRepo;
import com.enchere.repo.rechargement.RechargementRepo;
import com.enchere.service.common.CrudService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RechargementService extends CrudService<DemandeRechargement, RechargementRepo>{

    @Autowired
    MouvementRepo mouvementRepo;
    public RechargementService(RechargementRepo repo) {
        super(repo);
    }
    public List<DemandeRechargement> findAvailable(){
        List<DemandeRechargement> liste =  repo.demandeEnCours();

        for (int i = 0; i < liste.size(); i++) {
            liste.get(i).setUser((User) Hibernate.unproxy(liste.get(i).getUser()));
        }
        return liste;
    }
    public void refuse(DemandeRechargement demande){
        repo.refuse(demande.getId());
    }
    @Transactional(rollbackOn = Exception.class)
    public void accept(DemandeRechargement demande){
        Mouvement mvn = new Mouvement();
        mvn.setUser(demande.getUser());
        mvn.setTypeMouvement(new TypeMouvement(1));
        mvn.setMontant(demande.getMontant());
        mouvementRepo.save(mvn);
        repo.accept(demande.getId());
    }
}
