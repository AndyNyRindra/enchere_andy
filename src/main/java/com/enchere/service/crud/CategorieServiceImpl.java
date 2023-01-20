package com.enchere.service.crud;

import com.enchere.exception.CustomException;
import com.enchere.model.DemandeRechargement;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.User;
import com.enchere.repo.crud.CategorieRepo;
import com.enchere.repo.login.AdminRepo;
import com.enchere.service.common.CrudService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl extends CrudService<Categorie, CategorieRepo> implements CategorieService{

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
    public List<Categorie> top10Populaire() {
        List<Categorie> liste =  repo.top10Populaire();
        return liste;
    }

    @Override
    public List<Categorie> top10Rentable() {
        List<Categorie> liste =  repo.top10Rentable();
        return liste;
    }
}
