package com.enchere.service.crud;

import com.enchere.exception.CustomException;
import com.enchere.model.crud.Categorie;
import com.enchere.model.crud.Comission;
import com.enchere.repo.crud.CategorieRepo;
import com.enchere.repo.crud.ComissionRepo;
import com.enchere.service.common.CrudService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ComissionServiceImpl extends CrudService<Comission, ComissionRepo>  implements ComissionService{
    public ComissionServiceImpl(ComissionRepo repo) {
        super(repo);
    }

    @Override
    public Comission save(Comission comission) throws CustomException {
        return super.create(comission);
    }

    @Override
    public List<Comission> getAll() {
        return (List<Comission>) super.findAll();
    }

    @Override
    public Comission getById(long id) {
        return super.findById(id);
    }

    @Override
    public Comission update(Comission comission, long id) {
        return super.repo.update(comission.getValeur(),comission.getDate(),id);
    }

    @Override
    @Transactional(rollbackOn = SQLException.class)
    public Comission delete(long id) {
        Comission comission = super.findById(id);
        super.delete(id);
        return comission;
    }
}
