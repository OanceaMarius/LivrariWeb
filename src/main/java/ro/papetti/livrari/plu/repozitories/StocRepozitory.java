/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.pluriva.entity.SOrderCap;

/**
 *
 * @author MariusO
 */
public interface StocRepozitory extends JpaRepository<SOrderCap, Integer> {
        
    @Query(value="select  SUM(s.Stoc) StocDisponibil, ProdusId" +
"       from inv.Stoc (Nolock) s  where Stoc <>0 AND " +
"			case when isnull(:FirmaId,0) > 0 then s.FirmaId" +
"				else isnull(:FirmaId,0) end = isnull(:FirmaId,0) and " +
"			:GestiuneId = s.GestiuneId " +
"		group by ProdusId"
            , nativeQuery = true)
    public List<StocDisponibil> getStocDisponibilInGestiune(int FirmaId, int GestiuneId);
    
    
    
    
        @Query(value="DECLARE @GestiuneId int " +
"	SELECT top 1 @GestiuneId = GestiuneId from Papetti.dbo.tblGestiuni G " +
"	where Activ =1 AND Operational = 1 AND FirmaId = :FirmaId order by OrdineVanzare asc "+
"           select ProdusId,  SUM(s.Stoc) StocDisponibil" +
"       from inv.Stoc (Nolock) s  where Stoc <>0 AND " +
"			case when isnull(:FirmaId,0) > 0 then s.FirmaId" +
"				else isnull(:FirmaId,0) end = isnull(:FirmaId,0) and " +
"			@GestiuneId = s.GestiuneId" +
"		group by ProdusId"
            , nativeQuery = true)
    public Set<StocDisponibil>  getStocDisponibilInGestiuneOperationala(int FirmaId);
    
    
    

    
    
    
    
}
