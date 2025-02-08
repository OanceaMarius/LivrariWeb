/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.model;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author MariusO
 * @param <T> Clasa Entitate
 * @param <ID>
 * @param <Integer> Id-ul entitatii
 */
public interface BaseService<T, ID extends Integer> {
    
    public List<T> findAll();
    
        
    public List<T> saveAll(Iterable<T> entities);
    
    public T save(T entity);
    
    public Optional<T> findById(ID id);
    
      
    public long count();
    
    public void deleteById(ID id);
    
    public void delete(T entity);
        
    public void deleteAll();
    

}
