package com.enchere.service.crud;

import com.enchere.exception.CustomException;
import com.enchere.model.crud.Categorie;
import com.enchere.model.crud.Comission;

import java.util.List;

public interface ComissionService {
    Comission save(Comission comission) throws CustomException;

    List<Comission> getAll();

    Comission getById(long id);

    Comission update(Comission comission, long id);

    Comission delete(long id);
}
