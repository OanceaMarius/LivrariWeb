/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author MariusO
 * @param <T> Clasa Entitate
 * @param <ID> Clasa cheii primaare
 *
 */
public interface BaseService<T, ID extends Integer> {

    List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(ID id);

    Iterable<T> findAllById(Iterable<ID> ids);

    List<T> saveAll(Iterable<T> entities);

        T save(T entity);

    boolean existsById(ID id);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAll();

    void deleteAll(Iterable<? extends T> entities);

    void deleteAllById(Iterable<? extends ID> ids);

}
