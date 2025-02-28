/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.entity.SorderCap;
import ro.papetti.pluriva.entity.SorderPoz;

import java.util.List;
import java.util.Optional;


/**
 *
 * @author MariusO
 */
@Repository
@Transactional("plurivaTransactionManager")
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface SorderPozRepozitory extends JpaRepository<SorderPoz, Integer> {



    @Query("SELECT p FROM SorderPoz p WHERE p.sorderCapId=:sorderCapId")
    public <T> List<T> findPozDTOIBySorderCapId(int sorderCapId, Class<T> type);
    

    @Query("select c from SorderCap c join c.pozitii poz where  poz.sorderPozId = :sorderPozId")
    Optional<SorderCap> findSorderCapBySorderPozId(@Param("sorderPozId") Integer sorderPozId);

    @Query(value="SELECT p FROM SorderPoz p WHERE p.sorderPozId = :sorderPozId")
    @EntityGraph(value = "SorderPoz.complet")
    public Optional<SorderPoz> findEagerById(@NonNull int sorderPozId);


    @Query(value="SELECT p FROM SorderPoz p WHERE p.sorderCapId = :sorderCapId")
    @EntityGraph(value = "SorderPoz.complet")
    public List<SorderPoz> findEagerBySorderCapId(@NonNull int sorderCapId);


    @Query(value = "SELECT p FROM SorderPoz p WHERE p.sorderCapId = :sorderCapId")
    public List <SorderPoz> findBySorderCapId(@NonNull int sorderCapId);


    @Query("select p.porderPozId from PorderPoz p where p.sorderPozId=:sorderPozId")
    List<Integer> findPorderPozIdBySorderPozId(@Param("sorderPozId") int sorderPozId);


    @Query("select c from PorderCap c join c.pozitii poz where  poz.porderPozId = :porderPozId")
    Optional<PorderCap> findPorderCapByPorderPozId(@Param("porderPozId") Integer porderPozId);



}

