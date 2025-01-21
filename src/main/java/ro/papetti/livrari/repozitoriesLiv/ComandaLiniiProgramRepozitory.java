/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.repozitoriesLiv;

import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.LivrariTabele.entity.TblComandaLiniiProgram;

/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "livrariEntityManagerFactory")
public interface ComandaLiniiProgramRepozitory extends JpaRepository<TblComandaLiniiProgram, Integer> {
    
    @Query(value="SELECT * FROM TblComandaLiniiProgram t WHERE t.iDProgram = :idProgram", nativeQuery = true)
    public List<TblComandaLiniiProgram> findLiniiByIdProgram(int idProgram);
}
