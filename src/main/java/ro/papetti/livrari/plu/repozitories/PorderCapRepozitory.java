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
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.dtoi.PorderCapDTOI;
import ro.papetti.pluriva.entity.PorderCap;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface PorderCapRepozitory extends JpaRepository<PorderCap, Integer> {


    @Query(value = "SELECT * FROM PorderCap c  where c.pOrderCapId =:pOrderCapId", nativeQuery = true)
    public Optional<PorderCapDTOI> findDTOByPOrderCapId(@NonNull int pOrderCapId);

    @Query("select p from PorderCap p where p.dataLivrare = :dataLivrare")
    List<PorderCap> findByDataLivrare(@NonNull Date dataLivrare);




}

