package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.CPV;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface CPVRepozitory extends JpaRepository<CPV, Integer> {

    @Query("SELECT t FROM CPV t ")
    <T> List<T> findDTOAll(Class<T> type);

    @Query("select t from CPV t where t.cPVId = :cPVId")
    <T> Optional<T> findDTOById(@Param("cPVId") @NonNull Integer cPVId, Class<T> type);
}
