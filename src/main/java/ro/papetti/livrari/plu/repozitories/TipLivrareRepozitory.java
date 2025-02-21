/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.TipLivrare;

import java.util.List;
import java.util.Optional;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TipLivrareRepozitory extends JpaRepository<TipLivrare, Integer> {

    @Query("SELECT t FROM TipLivrare t ")
    <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from TipLivrare t where t.tipLivrareId = :tipLivrareId")
    <T> Optional<T> findDTOIById(@Param("tipLivrareId") @NonNull Integer tipLivrareId, Class<T> type);
    
}
