package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Judet;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface JudetRepozitory extends JpaRepository<Judet, Integer> {


    @Query("SELECT t FROM Judet t ")
    <T> List<T> findDTOAll(Class<T> type);

    @Query("select t from Judet t where t.judetID = :judetID")
    <T> Optional<T> findDTOById(@Param("judetID") @NonNull Integer judetID, Class<T> type);

}
