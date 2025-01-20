/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author MariusO
 */
public abstract class AbstractBaseService<T, ID, R extends  JpaRepository<T,ID>> implements BaseService {
    
    protected final R repository ;

    public AbstractBaseService (R rep) {
        this.repository = rep;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> saveAll(Iterable entities) {
        return repository.saveAll(entities);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

 
    public Optional <T> findById(ID id) {
        return repository.findById(id);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

  

    
    
    
    
}
