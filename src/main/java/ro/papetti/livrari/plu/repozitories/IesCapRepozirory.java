package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.IesCap;

import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface IesCapRepozirory extends JpaRepository<IesCap, Integer> {

    @Query("select I from IesCap I where I.iesCapId = :iesCapId")
    @EntityGraph(value = "IesCap.complet")
    Optional<IesCap> findEagerById(int iesCapId);
}
