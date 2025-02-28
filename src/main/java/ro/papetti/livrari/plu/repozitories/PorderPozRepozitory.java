/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.dto.PorderPozDto;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.entity.SorderCap;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface PorderPozRepozitory extends JpaRepository<PorderPoz, Integer> {

    @Query(value="SELECT p FROM PorderPoz p WHERE p.porderPozId = :porderPozId")
    @EntityGraph(value = "PorderPoz.complet")
    public Optional<PorderPoz> findEagerById(@NonNull int porderPozId);


    @Query(value="SELECT p FROM PorderPoz p WHERE p.porderCapId = :porderCapId")
    @EntityGraph(value = "PorderPoz.complet")
    public List<PorderPoz> findEagerByPorderCapId(@NonNull int porderCapId);

    @Query(value = "SELECT p FROM PorderPoz p WHERE p.porderCapId = :porderCapId")
    public <T> List <T> findDTOIByPorderCapId(@NonNull int porderCapId, Class<T> type);

    @Query(value = "SELECT p FROM PorderPoz p WHERE p.porderCapId = :porderCapId")
    public List <PorderPoz> findByPorderCapId(@NonNull int porderCapId);

    @Query("select c from SorderCap c join c.pozitii poz where  poz.sorderPozId = :sorderPozId")
    Optional<SorderCap> findSorderCapBySorderPozId(@Param("sorderPozId") Integer sorderPozId);


}
