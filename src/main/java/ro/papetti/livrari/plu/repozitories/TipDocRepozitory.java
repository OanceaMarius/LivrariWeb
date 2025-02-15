package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.TipDoc;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TipDocRepozitory extends JpaRepository<TipDoc, Integer> {

    @Query("SELECT t FROM TipDoc t ")
    <T> List<T> findDTOAll(Class<T> type);

    @Query("select t from TipDoc t where t.tipDocId = :tipDocId")
    <T> Optional<T> findDTOById(@Param("tipDocId") @NonNull Integer tipDocId, Class<T> type);


}
