package com.enchere.service.enchere;

import com.enchere.exception.ValueInvalideException;
import com.enchere.model.Mouvement;
import com.enchere.model.TypeMouvement;
import com.enchere.model.crud.Categorie;
import com.enchere.model.crud.DureeDefaut;
import com.enchere.model.enchere.CritereRechercheEnchere;
import com.enchere.model.enchere.Enchere;
import com.enchere.model.enchere.MiseEnchere;
import com.enchere.model.enchere.PhotosEnchere;
import com.enchere.model.login.User;
import com.enchere.repo.MouvementRepo;
import com.enchere.repo.crud.ComissionRepo;
import com.enchere.repo.crud.DureeDefautRepo;
import com.enchere.repo.enchere.EnchereRepo;
import com.enchere.repo.enchere.MiseEnchereRepo;
import com.enchere.repo.enchere.PhotosEnchereRepo;
import com.enchere.service.common.CrudService;
import com.enchere.service.common.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.enchere.util.DateUtil.getDateNow;

@Service
public class EnchereService extends CrudService<Enchere, EnchereRepo> {

    @PersistenceContext
    EntityManager entity;

    @Autowired
    private ComissionRepo comissionRepo;

    @Autowired
    private DureeDefautRepo dureeDefautRepo;

    @Autowired
    private PhotosEnchereRepo photosEnchereRepo;

    @Autowired
    private MiseEnchereRepo miseEnchereRepo;

    @Autowired
    private MouvementRepo mouvementRepo;


    public EnchereService(EnchereRepo repo) {
        super(repo);
    }

    public List<Enchere> rechercheMultiCritere(CritereRechercheEnchere criteres) throws Exception {
        Query query = entity.createNativeQuery(criteres.getCondition(), Enchere.class);
        List<Enchere> encheres = query.getResultList();
        for (Enchere enchere : encheres) {
            enchere.setCategorie((Categorie) Hibernate.unproxy(enchere.getCategorie()));
            enchere.setUser((User) Hibernate.unproxy(enchere.getUser()));
            for (MiseEnchere miseEnchere: enchere.getMises()) {
                miseEnchere.setUser((User) Hibernate.unproxy(miseEnchere.getUser()));
            }
        }
        return encheres;
    }

    public List<Enchere> findAllByUser(User user) {
        return repo.findAllByUser(user);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Enchere create(Enchere enchere) throws ValueInvalideException {
        if (enchere.getDuree() == null) {
            enchere.setDuree(dureeDefautRepo.findLast().getDuree());
        }
        enchere.setComission(comissionRepo.findLast().getValeur() / 100);
        // Transform hour to milisecond

        Date dateFin = new Date((long) (enchere.getDateDebut().getTime() + enchere.getDuree() * 3600 * 1000));
        enchere.setDateFin(dateFin);
        enchere.setStatus(0);
        repo.save(enchere);
        for (PhotosEnchere photosEnchere: enchere.getPhotos()) {
            photosEnchere.setIdEnchere(enchere.getId());
            photosEnchereRepo.save(photosEnchere);
        }
        return enchere;
    }

    public List<Enchere> findAllEnCours() {
        return repo.findAllByStatusAndDateFinBefore(1, getDateNow());
    }

    public List<Enchere> findAllNonCommence() {
        return repo.findAllByStatusAndDateDebutBefore(0, getDateNow());
    }
    public void begin() {
        List<Enchere> encheres = findAllNonCommence();
        System.out.println(encheres.size());
        for (Enchere enchere: encheres) {
            enchere.setStatus(1);
            repo.save(enchere);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public List<Enchere> getJusteTermine() {

        List<Enchere> encheresEnCours = findAllEnCours();
        List<Enchere> result = new ArrayList<>();
        for (Enchere enchere: encheresEnCours) {
            enchere.setStatus(10);
            MiseEnchere derniereMise = miseEnchereRepo.findByIdEnchereAndEstPlusHaut(enchere.getId(), true);
            if (derniereMise != null) {
                Mouvement mvn = new Mouvement();
                mvn.setUser(derniereMise.getUser());
                mvn.setTypeMouvement(new TypeMouvement(2));
                mvn.setMontant(derniereMise.getMontant() * (1 - enchere.getComission()));
                mouvementRepo.save(mvn);
            }
            repo.save(enchere);
            for (MiseEnchere miseEnchere: enchere.getMises()) {
                miseEnchere.setUser((User) Hibernate.unproxy(miseEnchere.getUser()));
            }
            result.add(enchere);

        }
        return result;
    }
}
