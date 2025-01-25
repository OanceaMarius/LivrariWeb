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
import ro.papetti.PlurivaTabele.entity.TblUnitate;
import ro.papetti.livrari.model.Unitate;

/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TblUnitateRepozitory extends JpaRepository<TblUnitate, Integer> {
    
    public List<TblUnitate> findByDenumireUnitate(String denumireUnitate);
    
    @Query(value="SELECT U.unitateId, " + 
"        U.PartenerId, " +
"        U.DenumireUnitate, " + 
"        P.DenumirePartener, " +
"        P.TipFirmaId," +
"        P.CodFiscal," +
"        P.AtributFiscal," +
"        U.TaraID," +
"        T.DenumireTara," +
"        U.JudetID," +
"        J.DenumireJudet," +
"        U.LocalitateID," +
"        L.DenumireLocalitate," +
"        P.SiteWeb," +
"        U.Longitudine," +
"        U.Latitudine" +
"  FROM tblUnitate U " +
"	inner join tblPartener P on U.PartenerId = P.PartenerId" +
"       left join tblTara T on T.TaraId = U.TaraId " +
"       left join tblJudet J on J.JudetId = U.JudetId " +
"       left join tblLocalitate L on L.LocalitateId=U.LocalitateId "+
"  where UnitateID = :unitateId", nativeQuery = true)
    public Unitate findUnitateWrapperById(int unitateId);
}
