package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Cpv;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface CpvRepozitory extends JpaRepository<Cpv, Integer> {

    @Query("SELECT t FROM Cpv t ")
    <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from Cpv t where t.cPVId = :cPVId")
    <T> Optional<T> findDTOIById(@Param("cPVId") @NonNull Integer cPVId, Class<T> type);
}
