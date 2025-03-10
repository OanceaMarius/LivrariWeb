package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Gestiune;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface GestiuneRepozitory extends JpaRepository<Gestiune, Integer> {

}
