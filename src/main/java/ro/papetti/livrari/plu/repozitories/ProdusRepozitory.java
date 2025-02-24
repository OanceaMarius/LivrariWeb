package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Produs;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface ProdusRepozitory extends JpaRepository<Produs, Integer> {

    @Query("SELECT t FROM Produs t ")
    <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from Produs t where t.produsId = :produsId")
    <T> Optional<T> findDTOIById(@Param("produsId") @NonNull Integer produsId, Class<T> type);

    @Query("select t from Produs t where t.produsId = :produsId")
    @EntityGraph(attributePaths = {"um", "tva", "cpv", "brand"})
    Optional<Produs> findEagerById(int produsId);


    Optional<Produs> findById(int produsId);



}
