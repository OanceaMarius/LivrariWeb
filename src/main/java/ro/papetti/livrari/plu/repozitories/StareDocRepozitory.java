package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.StareDoc;

import java.util.List;
import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface StareDocRepozitory extends JpaRepository<StareDoc, Integer> {

    @Query("SELECT t FROM StareDoc t ")
    <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from StareDoc t where t.stareId = :stareId")
    <T> Optional<T> findDTOIById(@Param("stareId") @NonNull Integer stareId, Class<T> type);
}
