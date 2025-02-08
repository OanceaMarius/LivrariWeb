/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.pluriva.entity.SOrderCap;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface SOrderCapRepozitory extends JpaRepository<SOrderCap, Integer> {
    
    public Optional<List<SOrderCap>> findByDataLivrare(Date dataLivrare);
    
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
    // TODO: sa scap de SqlErp.dbo.TableDocumente in getCantitatiLivrate
    
    
        @Query(value=
	"select SOrderPozParentId pozId, SUM(Cant) cantitate " +
	"from SOrderPoz (nolock) "+
	"where IntrPozId is not null AND Cant>0  "+
	"	and SOrderPozParentId in "+
	"		(select SOrderPozId from dbo.SOrderPoz (nolock) where SOrderCapId = :sOrderCapId) "+
	"group by SOrderPozParentId"
         , nativeQuery = true)
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId);
}
