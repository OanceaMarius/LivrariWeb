/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Unitate;


/**
 *
 * @author MariusO
 */
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface UnitateRepozitory extends JpaRepository<Unitate, Integer> {

    @Query("""
            select u from Unitate u
            where u.denumireUnitate like concat('%', :denumireUnitate, '%') 
            or u.partener.denumirePartener like concat('%', :denumireUnitate, '%')""")
    List<Unitate> findByDenumireUnitate(@Param("denumireUnitate") String denumireUnitate);

@Query("""
            select u from Unitate u
            where u.denumireUnitate like concat('%', :denumireUnitate, '%') 
            or u.partener.denumirePartener like concat('%', :denumireUnitate, '%')""")
    <T> List<T> findDTOByDenumireUnitate(@Param("denumireUnitate") String denumireUnitate, Class<T> type);


    @Query("select u from Unitate u where u.denumireUnitate like :denumireUnitate or u.partener.denumirePartener like :denumireUnitate")
    List<Unitate> findByDenumire(@Param("denumireUnitate") String denumireUnitate);


    @Query("SELECT t FROM Unitate t ")
    <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from Unitate t where t.unitateID = :unitateID")
    <T> Optional<T> findDTOIById(@Param("unitateID") @NonNull Integer unitateID, Class<T> type);

    @Query("""
            select u from Unitate u
            where u.denumireUnitate like concat('%', :denumireUnitate, '%') or u.partener.denumirePartener like concat('%', :denumireUnitate, '%')""")
    List<Unitate> findByNume(@Param("denumireUnitate") String denumireUnitate, @Param("denumirePartener") String denumirePartener);


}
