package com.enchere.service.crud;

import com.enchere.exception.CustomException;
import com.enchere.model.DemandeRechargement;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.User;
import com.enchere.model.stat.CategoriePopulaire;
import com.enchere.model.stat.CategorieRentable;
import com.enchere.repo.crud.CategorieRepo;
import com.enchere.repo.login.AdminRepo;
import com.enchere.repo.stat.CategoriePopulaireRepo;
import com.enchere.repo.stat.CategorieRentableRepo;
import com.enchere.service.common.CrudService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl extends CrudService<Categorie, CategorieRepo> implements CategorieService{

    @Autowired
    private CategoriePopulaireRepo categoriePopulaireRepo;

    @Autowired
    private CategorieRentableRepo categorieRentableRepo;

    public CategorieServiceImpl(CategorieRepo repo) {
        super(repo);
    }

    @Override
    public Categorie save(Categorie categorie) throws CustomException {
        return super.create(categorie);
    }

    @Override
    public List<Categorie> getAll() {
        return (List<Categorie>) super.findAll();
    }

    @Override
    public Categorie getById(long id) {
        return super.findById(id);
    }

    @Override
    public Categorie update(Categorie categorie, long id) {
        return super.repo.update(categorie.getNom(),id);
    }

    @Override
    public Categorie delete(long id) {
        Categorie cat = super.findById(id);
        super.delete(id);
        return cat;
    }

    @Override
    public List<CategoriePopulaire> top10Populaire() {
        List<CategoriePopulaire> liste =  categoriePopulaireRepo.findAll();
        return liste;
    }

    @Override
    public List<CategorieRentable> top10Rentable() {
        List<CategorieRentable> liste =  categorieRentableRepo.findAll();
        return liste;
    }
}
