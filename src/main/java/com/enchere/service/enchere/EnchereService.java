package com.enchere.service.enchere;

import com.enchere.model.crud.Categorie;
import com.enchere.model.crud.DureeDefaut;
import com.enchere.model.enchere.CritereRechercheEnchere;
import com.enchere.model.enchere.Enchere;
import com.enchere.model.enchere.PhotosEnchere;
import com.enchere.model.login.User;
import com.enchere.repo.crud.ComissionRepo;
import com.enchere.repo.crud.DureeDefautRepo;
import com.enchere.repo.enchere.EnchereRepo;
import com.enchere.repo.enchere.PhotosEnchereRepo;
import com.enchere.service.common.CrudService;
import com.enchere.service.common.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public EnchereService(EnchereRepo repo) {
        super(repo);
    }

    public List<Enchere> rechercheMultiCritere(CritereRechercheEnchere criteres) throws Exception {
        Query query = entity.createNativeQuery(criteres.getCondition(), Enchere.class);
        List<Enchere> encheres = query.getResultList();
        for (Enchere enchere : encheres) {
            enchere.setCategorie((Categorie) Hibernate.unproxy(enchere.getCategorie()));
            enchere.setUser((User) Hibernate.unproxy(enchere.getUser()));
        }
        return encheres;
    }

    public List<Enchere> findAllByUser(User user) {
        return repo.findAllByUser(user);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Enchere create(Enchere enchere) {
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
        return repo.findAllByStatus(1);
    }


    @Transactional(rollbackOn = Exception.class)
    public List<Enchere> getJusteTermine() {
        List<Enchere> encheresEnCours = findAllEnCours();
        List<Enchere> result = new ArrayList<>();
        for (Enchere enchere: encheresEnCours) {
            if (enchere.isEnchereOver()) {
                enchere.setStatus(10);
                repo.save(enchere);
                result.add(enchere);
            }
        }
        return result;
    }
}
