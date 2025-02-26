/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.FollowUp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author MariusO
 */

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface FollowUpRepozitory extends JpaRepository<FollowUp, Integer> {


    @Query("select f from FollowUp f where f.dataCreare > :dataCreare")
    List<FollowUp> findByDataCreareDupa(@Param("dataCreare") Date dataCreare);

    @Query("select f from FollowUp f where f.tipActivitate.tipActivitateID = :tipActivitateID")
    List<FollowUp> findByTipActivitate(@Param("tipActivitateID") Integer tipActivitateID);

    @Query("""
            select f from FollowUp f
            where f.tipActivitate.tipActivitateID = :tipActivitateID and f.dataCreare > :dataCreare""")
    List<FollowUp> findByTipActivitateSiDataCreareDupa(@Param("tipActivitateID") Integer tipActivitateID, @Param("dataCreare") Date dataCreare);

    @Query(value = "select f from FollowUp f where f.followupId= :followupId")
    @EntityGraph(value = "FollowUp.complet")
    Optional<FollowUp>findEagerById(@Param("followupId") int followupId);

    @Query("select f from FollowUp f where f.followupId = :followupId")
    List<FollowUp> ff(@Param("followupId") Integer followupId);

}
