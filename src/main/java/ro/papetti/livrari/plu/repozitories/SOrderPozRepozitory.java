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
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
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
    public List<StocDisponibil>  getStocDisponibilInGestiuneOperationala(int FirmaId);
    
    
    
        @Query(value="select SOrderPozId pozId, SUM(SumLiv) cantitate from( " +
"	select P.SOrderPozId, sum(P.CantIesire) SumLiv"+
"			from inv.IesPoz P "+
"			inner join inv.IesCap C on P.IesCapId = C.IesCapId"+
"			inner join dbo.SorderPoz SP on SP.SOrderPozId = P.SOrderPozId "+
"			where TipDocId in  (select TipDocId from SqlErp.dbo.TableDocumente() D where D.FirmaId = :firmaId and DocId in (5, 8) and CuGestiune=1 and Inactiv = 0) "+
"				AND SP.SOrderCapId = :sOrderCapId "+
"				AND C.DataAnulare is null and C.DataValidare is not null "+
"				and SOrderPozParentId is null "+
"			group by P.SOrderPozId"+
"			"+
"	union All "+

"	select  SP.SOrderPozParentId, sum(P.CantIesire) "+
"			from inv.IesPoz P "+
"			inner join inv.IesCap C on P.IesCapId = C.IesCapId "+
"			inner join SorderPoz SP on SP.SOrderPozId = P.SOrderPozId "+
"			where TipDocId in  (select TipDocId from SqlErp.dbo.TableDocumente() D where D.FirmaId = :firmaId and DocId in (5, 8) and CuGestiune=1 and Inactiv = 0)"+
"				AND SP.SOrderCapId = :sOrderCapId "+
"				AND C.DataAnulare is null and C.DataValidare is not null "+

"			group by  SP.SOrderPozParentId "+
"	) as A "+
"       where SOrderPozId is not null "+
"	group by SOrderPozId "
            , nativeQuery = true)
    public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId);
}
