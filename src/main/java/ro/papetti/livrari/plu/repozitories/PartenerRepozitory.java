package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Partener;

import java.util.Optional;
@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface PartenerRepozitory extends JpaRepository<Partener, Integer> {

    @EntityGraph(attributePaths = {"tipFirma", "tara", "judet", "localitate", "tipStrada", "userIntroducere", "userModificare"})
    Optional<Partener> findEagerByPartenerID(int partenerId);



}
