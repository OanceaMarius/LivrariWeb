package ro.papetti.livrari.liv.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@PersistenceContext(unitName = "livrariEntityManagerFactory")
public interface CoordonateFixeRepozitory extends JpaRepository<ro.papetti.LivrariTabele.entity.CoordonateFixe, Integer> {
}
