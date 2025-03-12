package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.IesPoz;

import java.util.List;
import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface IesPozRepozitory extends JpaRepository<IesPoz, Integer> {

    List<IesPoz> findByIesCapId(int iesCapId);

    @Query("select I from IesPoz I where I.iesCapId = :iesCapId")
    @EntityGraph(value = "IesPoz.complet")
    List<IesPoz> findEagerByIesCapId(int iesCapId);


    @Query("select I from IesPoz I where I.iesPozId = :iesPozId")
    @EntityGraph(value = "IesPoz.complet")
    Optional<IesPoz> findEagerById(int iesPozId);
    
}

