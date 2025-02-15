package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.TipStrada;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TipStradaRepozitory extends JpaRepository<TipStrada, Integer> {




}
