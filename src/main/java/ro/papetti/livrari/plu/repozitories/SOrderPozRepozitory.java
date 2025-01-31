/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.SOrderPoz;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface SOrderPozRepozitory extends JpaRepository<SOrderPoz, Integer> {
    
    @Query(value="SELECT * FROM SOrderPoz p WHERE p.sOrderCapId = :sOrderCapId", nativeQuery = true)
    public List<SOrderPoz> findPozitiiBySOrderCapId(int sOrderCapId);
}
