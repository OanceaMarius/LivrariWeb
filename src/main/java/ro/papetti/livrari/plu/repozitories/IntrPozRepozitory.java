package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.IntrPoz;

import java.util.List;
import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface IntrPozRepozitory extends JpaRepository<IntrPoz, Integer> {


    List<IntrPoz> findByIntrCapId(int intrCapId);

    @Query("select I from IntrPoz I where I.intrCapId = :intrCapId")
    @EntityGraph(value = "IntrPoz.complet")
    List<IntrPoz> findEagerByIntrCapId(int intrCapId);


    @Query("select I from IntrPoz I where I.intrPozId = :intrPozId")
    @EntityGraph(value = "IntrPoz.complet")
    Optional<IntrPoz> findEagerById(int intrPozId);
}
