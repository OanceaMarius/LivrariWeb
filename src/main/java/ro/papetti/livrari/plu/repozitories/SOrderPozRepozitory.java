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
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.pluriva.entity.SOrderPoz;


/**
 *
 * @author MariusO
 */
@Repository
@Transactional("plurivaTransactionManager")
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface SOrderPozRepozitory extends JpaRepository<SOrderPoz, Integer> {
    
//    @Query(value="SELECT * FROM SOrderPoz p WHERE p.sOrderCapId = :sOrderCapId", nativeQuery = true)
//    public List<SOrderPozDTO> findPozitiiBySOrderCapId(int sOrderCapId);

//    @Query(value="SELECT "
//            + "p.sOrderPozId AS sOrderPozId, "
//            + "p.sOrderPozParentId as sOrderPozParentId, "
//            + "p.produs as produs, "
//            + "p.cant as cant, "
//            + "p.pretValuta as pretValuta, "
//            + "p.valutaId valutaId, "
//            + "p.cantLivrata as cantLivrata, "
//            + "p.intrPozId as intrPozId, "
//            + "p.furnizorId as furnizorId, "
//            + "p.cantRezervata as cantRezervata, "
//            + "p.pOrderPoz as POrderPoz  "
//            + "FROM SOrderPoz p WHERE p.sOrderCapId = :sOrderCapId")
//    public <T> List<T> findPozitiiDTOBySOrderCapId(int sOrderCapId,Class<T> type);
    
//        @Query(value="FROM SOrderPoz p WHERE p.sOrderCapId = :sOrderCapId")
    
    @Query("SELECT p FROM SOrderPoz p WHERE p.sOrderCap.sOrderCapId=:sOrderCapId")
    public <T> List<T> findBySOrderCapSOrderCapId(int sOrderCapId,Class<T> type);
    

    

}

