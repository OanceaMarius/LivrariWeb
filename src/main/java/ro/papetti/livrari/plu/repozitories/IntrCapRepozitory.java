package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.IntrCap;

import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface IntrCapRepozitory extends JpaRepository<IntrCap, Integer> {

    @Query("select I from IntrCap I where I.intrCapId = :intrCapId")
    @EntityGraph(value = "IntrCap.complet")
    Optional<IntrCap> findEagerById(int intrCapId);

}
