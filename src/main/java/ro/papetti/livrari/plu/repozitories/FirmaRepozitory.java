package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Firma;

import java.util.List;
import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface FirmaRepozitory extends JpaRepository<Firma,Integer> {

    @Query("SELECT f FROM Firma f WHERE f.firmaId = :firmaId")
    @EntityGraph(value = "Firma.complet")
    Optional<Firma> findEagerById(Integer firmaId);


    @Query("SELECT f FROM Firma f")
    @EntityGraph(value = "Firma.complet")
    List<Firma> findEagerAll();
}
