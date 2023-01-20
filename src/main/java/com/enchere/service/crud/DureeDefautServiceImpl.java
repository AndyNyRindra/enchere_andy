package com.enchere.service.crud;

import com.enchere.exception.CustomException;
import com.enchere.model.crud.Categorie;
import com.enchere.model.crud.DureeDefaut;
import com.enchere.repo.crud.CategorieRepo;
import com.enchere.repo.crud.DureeDefautRepo;
import com.enchere.service.common.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DureeDefautServiceImpl extends CrudService<DureeDefaut, DureeDefautRepo> implements DureeDefautService{
    public DureeDefautServiceImpl(DureeDefautRepo repo) {
        super(repo);
    }

    @Override
    public DureeDefaut save(DureeDefaut dureeDefaut) throws CustomException {
        return super.create(dureeDefaut);
    }

    @Override
    public List<DureeDefaut> getAll() {
        return (List<DureeDefaut>) super.findAll();
    }

    @Override
    public DureeDefaut getById(long id) {
        return super.findById(id);
    }

    @Override
    public DureeDefaut update(DureeDefaut dureeDefaut, long id) {
        return super.repo.update(dureeDefaut.getDuree(),dureeDefaut.getDate(),id);
    }

    @Override
    public DureeDefaut delete(long id) {
        DureeDefaut d = super.findById(id);
        super.delete(id);
        return d;
    }
}
