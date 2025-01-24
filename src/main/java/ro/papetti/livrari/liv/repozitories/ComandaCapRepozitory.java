/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.liv.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.papetti.LivrariTabele.entity.ComandaCap;

/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "livrariEntityManagerFactory")
public interface ComandaCapRepozitory extends JpaRepository<ComandaCap, Integer> {
    
//    @Query(value="select * from tblComandaAntetProgram ca where Anulata = 0 and Vizitat=0", nativeQuery=true)
//    public List<TblComandaAntetProgram> findComenzileDeLivratInData( Date data);
}
