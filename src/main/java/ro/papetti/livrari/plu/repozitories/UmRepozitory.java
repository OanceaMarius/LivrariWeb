package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Um;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface UmRepozitory extends JpaRepository<Um, Integer> {

    @Query("SELECT t FROM Um t ")
     <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from Um t where t.umId = :umId")
    <T> Optional<T> findDTOIById(@Param("umId") @NonNull Integer umId, Class<T> type);



}
