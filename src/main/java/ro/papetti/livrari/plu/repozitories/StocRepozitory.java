/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.pluriva.entity.FurnizorProdus;
import ro.papetti.pluriva.entity.SorderCap;

import java.util.List;

/**
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface StocRepozitory extends JpaRepository<SorderCap, Integer> {


    @Query(value = "SELECT top 1  GestiuneId from tblGestiuni G " +
            "where Activ =1 AND Operational = 1 AND FirmaId = :firmaId order by OrdineVanzare asc", nativeQuery = true)
    @Transactional(readOnly = true)
    public int getGestiuneOperationalaPeFirma(int firmaId);


    @Query(value = "select ProdusId, SUM(s.Stoc) StocDisponibil " +
            "from inv.Stoc s  where Stoc <>0 AND " +
            "s.FirmaId  = :firmaId and " +
            "s.GestiuneId  = :gestiuneId and " +
            "ProdusId IN (:produsIds) " +
            "group by ProdusId", nativeQuery = true)
    @Transactional(readOnly = true)
    public List<StocDisponibil> getStocDisponibilInGestiuneFiltrat(
            @Param("firmaId") int firmaId,
            @Param("gestiuneId") int gestiuneId,
            @Param("produsIds") List<Integer> produsIdList);

    @Query(value = "select ProdusId,  SUM(s.Stoc) StocDisponibil " +
            "from inv.Stoc (Nolock) s  where Stoc <>0 AND " +
            "s.FirmaId  = :firmaId and " +
            "s.GestiuneId  = :gestiuneId " +
            "group by ProdusId", nativeQuery = true)
    @Transactional(readOnly = true)
    public List<StocDisponibil> getStocDisponibilInGestiune(int firmaId, int gestiuneId);


    @Query(value = "select furnizorId, produsId, pretValuta, valutaId," +
            "denumireProdusFurnizor, codProdusFurnizor, furnizorDefault, " +
            "pretCost, pretValutaRedus, cantMinCmd, cantCmdMultiplu, stoc  " +
            "from tblFurnizoriProduse " +
            "where furnizorId=:furnizorId " +
            "and firmaId = :firmaId " +
            "and divizieId = :divizieId "+
            "and produsId IN (:produsIds)", nativeQuery = true)
    @Transactional(readOnly = true)
    public List<FurnizorProdus> getProduseLaFurnizor(
            @Param("furnizorId")int furnizorId,
            @Param("firmaId")int firmaId,
            @Param("divizieId")int divizieId,
            @Param("produsIds")List<Integer> produsIdList
            );


}
