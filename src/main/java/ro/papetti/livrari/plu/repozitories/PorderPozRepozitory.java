/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import java.util.List;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.PorderPoz;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface PorderPozRepozitory extends JpaRepository<PorderPoz, Integer> {
    
    
    @Query(value="SELECT * FROM PorderPoz p WHERE p.pOrderCapId = :pOrderCapId", nativeQuery = true)
    public List<PorderPoz> findByPOrderCapId(@NonNull int pOrderCapId);

    @Query(value = "SELECT p FROM PorderPoz p WHERE p.porderCap.pOrderCapId = :pOrderCapId")
    public <T> List <T> findDTOByPOrderCapId(@NonNull int pOrderCapId, Class<T> type);




}
