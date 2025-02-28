/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.dtoi.PorderCapDTOI;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.SorderCap;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface PorderCapRepozitory extends JpaRepository<PorderCap, Integer> {

    @Query(value = "SELECT c FROM PorderCap c  where c.porderCapId =:porderCapId")
    @EntityGraph(value = "PorderCap.complet")
    public Optional<PorderCap> findEagerById(@NonNull int porderCapId);

    @Query(value = "SELECT * FROM PorderCap c  where c.porderCapId =:porderCapId", nativeQuery = true)
    public Optional<PorderCapDTOI> findDTOByPOrderCapId(@NonNull int porderCapId);

    @Query("select p from PorderCap p where p.dataLivrare = :dataLivrare")
    List<PorderCap> findByDataLivrare(@NonNull Date dataLivrare);

    @Query("select c from PorderCap c join c.pozitii poz where  poz.porderPozId = :porderPozId")
    Optional<PorderCap> findPorderCapByPorderPozId(@Param("porderPozId") Integer porderPozId);

    @Query("select c from SorderCap c join c.pozitii poz where  poz.sorderPozId = :sorderPozId")
    Optional<SorderCap> findSorderCapBySorderPozId(@Param("sorderPozId") Integer sorderPozId);

}

