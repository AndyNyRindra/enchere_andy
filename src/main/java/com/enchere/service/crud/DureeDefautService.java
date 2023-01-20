package com.enchere.service.crud;

import com.enchere.exception.CustomException;
import com.enchere.model.crud.Comission;
import com.enchere.model.crud.DureeDefaut;

import java.util.List;

public interface DureeDefautService {
    DureeDefaut save(DureeDefaut dureeDefaut) throws CustomException;

    List<DureeDefaut> getAll();

    DureeDefaut getById(long id);

    DureeDefaut update(DureeDefaut dureeDefaut, long id);

    DureeDefaut delete(long id);
}
