/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.FollowUp;

/**
 *
 * @author MariusO
 */

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface FollowUpRepozitory extends JpaRepository<FollowUp, Integer> {
    
}
