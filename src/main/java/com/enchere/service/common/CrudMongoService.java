package com.enchere.service.common;


import java.util.List;

import com.enchere.exception.CustomException;
import org.springframework.data.mongodb.repository.MongoRepository;

public class CrudMongoService<E,  R extends MongoRepository<E, String>> implements Service<E> {

    protected final R repo;

    public CrudMongoService(R repo) {
        this.repo = repo;
    }

    @Override
    public E create(E obj) throws CustomException {
        return repo.save(obj);
    }

    @Override
    public E update(E obj) throws CustomException {
        return repo.save(obj);
    }

    @Override
    public void delete(Object id) {
        repo.deleteById((String) id);
    }

    @Override
    public E findById(Object id) {
        return repo.findById((String)id).orElse(null);
    }

    @Override
    public Iterable<E> findAll() {
        return repo.findAll();
    }
    @Override
    public List<E>saveAll(List<E> iterable){
        return repo.saveAll(iterable);
    }

}
