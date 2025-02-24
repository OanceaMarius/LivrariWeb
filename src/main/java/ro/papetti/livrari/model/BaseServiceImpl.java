/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MariusO
 */

/**
 * 
 * @author MariusO
 * @param <T> Clasa Entitate
 * @param <R> Repozitory pt entitatea T
 */
@Transactional
public abstract class BaseServiceImpl<T, R extends  JpaRepository<T,Integer>> implements BaseService<T, Integer> {
    
    protected final R rep ;

    public BaseServiceImpl (R repozitory) {
        this.rep = repozitory;
 
    }

    @Override
    public List<T> findAll() {
        return rep.findAll();
    }


    public List<T> findAll(Sort sort){
        return rep.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable){
        return rep.findAll(pageable);
    }

    @Override
    public List<T> saveAll(Iterable entities) {
        return rep.saveAll(entities);
    }
    

    @Override
    public T save(T entity) {
        return rep.save(entity);
    }

    
    @Override
    public Optional <T> findById(Integer id) {
        return rep.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return rep.existsById(id);
    }

    @Override
    public long count() {
        return rep.count();
    }

    
    @Override
    public void deleteById(Integer id) {
        rep.deleteById(id);
    }

    
    @Override
    public void deleteAll(Iterable entities) {
        rep.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        rep.deleteAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<Integer> ids) {
        return rep.findAllById(ids);
    }

    @Override
    public void delete(T entity) {
        rep.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        rep.deleteAllById(ids);
    }

  

    
    
    
    
}
