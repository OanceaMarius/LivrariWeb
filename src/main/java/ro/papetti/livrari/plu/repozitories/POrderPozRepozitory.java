/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.POrderPoz;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface POrderPozRepozitory extends JpaRepository<POrderPoz, Integer> {
    
    
    @Query(value="SELECT * FROM POrderPoz p WHERE p.pOrderCapId = :pOrderCapId", nativeQuery = true)
    public List<POrderPoz> findByPOrderCapId(int pOrderCapId);

    @Query(value = "SELECT * FROM POrderPoz p WHERE p.pOrderCapId = :pOrderCapId", nativeQuery = true)
    public <T> List <T> findDTOByPOrderCapId(int pOrderCapId, Class<T> type);


}
