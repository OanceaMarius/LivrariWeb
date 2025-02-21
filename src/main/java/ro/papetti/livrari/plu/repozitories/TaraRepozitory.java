package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Tara;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TaraRepozitory extends JpaRepository<Tara, Integer> {

    @Query("SELECT t FROM Tara t ")
    <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from Tara t where t.taraID = :taraID")
    <T> Optional<T> findDTOIById(@Param("taraID") @NonNull Integer taraID, Class<T> type);
}
