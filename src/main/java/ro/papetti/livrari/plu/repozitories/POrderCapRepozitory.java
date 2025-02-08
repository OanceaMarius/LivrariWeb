/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.POrderCap;


/**
 *
 * @author MariusO
 */
@Repository

//@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface POrderCapRepozitory extends JpaRepository<POrderCap, Integer> {
    
    public Optional<List<POrderCap>> findByDataLivrare(Date dataLivrare);
}
