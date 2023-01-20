package com.enchere.service.crud;

import com.enchere.exception.CustomException;
import com.enchere.model.crud.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie save(Categorie categorie) throws CustomException;

    List<Categorie> getAll();

    Categorie getById(long id);

    Categorie update(Categorie categorie, long id);

    Categorie delete(long id);

    List<Categorie> top10Populaire();
    List<Categorie> top10Rentable();
}
