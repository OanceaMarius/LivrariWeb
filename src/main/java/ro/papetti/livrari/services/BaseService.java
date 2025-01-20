/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.services;

import java.util.List;

/**
 *
 * @author MariusO
 * @param <T>
 * @param <Integer>
 */
public interface BaseService<T, ID> {
    
    public List<T> findAll();
    
    public List<T> saveAll(Iterable<T> entities);
    
//    public T save(T entity);
    
//    public Optional<T> findById(ID id);
    
//    public boolean existsById(ID id);
    
    public long count();
    
//    public void deleteById(ID id);
    
//    public void delete(T entity);
    
    public void deleteAll(Iterable<T> entities);
    
    public void deleteAll();
    

}
