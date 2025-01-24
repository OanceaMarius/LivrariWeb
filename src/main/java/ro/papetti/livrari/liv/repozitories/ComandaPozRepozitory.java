/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.liv.repozitories;

import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.LivrariTabele.entity.ComandaPoz;

/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "livrariEntityManagerFactory")
public interface ComandaPozRepozitory extends JpaRepository<ComandaPoz, Integer> {
    
    @Query(value="SELECT * FROM ComandaPoz t WHERE t.CapId = :capId", nativeQuery = true)
    public List<ComandaPoz> findLiniiByIdProgram(int capId);
    
    
    
//    @Procedure(procedureName = "Test")
//    public List<String> getTest(@Param("nr") int nr);
}

