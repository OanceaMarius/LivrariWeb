package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.TermenPlata;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TermenPlataRepozitory extends JpaRepository<TermenPlata, Integer> {

    @Query("SELECT t FROM TermenPlata t ")
    <T> List<T> findDTOAll(Class<T> type);

    @Query("select t from TermenPlata t where t.termenPlataID = :termenPlataID")
    <T> Optional<T> findDTOById(@Param("termenPlataID") @NonNull Integer termenPlataID, Class<T> type);


}
