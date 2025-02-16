/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.dto.POrderCapDTOI;
import ro.papetti.pluriva.entity.POrderCap;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface POrderCapRepozitory extends JpaRepository<POrderCap, Integer> {


    @Query(value = "SELECT * FROM POrderCap c  where c.pOrderCapId =:pOrderCapId", nativeQuery = true)
    public Optional<POrderCapDTOI> findDTOByPOrderCapId(@NonNull int pOrderCapId);

    @Query("select p from POrderCap p where p.dataLivrare = :dataLivrare")
    List<POrderCap> findByDataLivrare(@NonNull Date dataLivrare);




}

