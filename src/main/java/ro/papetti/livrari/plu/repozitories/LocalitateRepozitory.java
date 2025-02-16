package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Localitate;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface LocalitateRepozitory extends JpaRepository<Localitate, Integer> {

    @Query("SELECT t FROM Localitate t ")
    <T> List<T> findDTOAll(Class<T> type);

    @Query("select t from Localitate t where t.localitateID = :localitateID")
    <T> Optional<T> findDTOById(@Param("localitateID") @NonNull Integer umId, Class<T> type);


}
