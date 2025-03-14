package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.ModPlata;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface ModPlataRepozitory extends JpaRepository<ModPlata, Integer> {

    @Query("SELECT t FROM ModPlata t ")
    <T> List<T> findDTOAll(Class<T> type);

    @Query("select t from ModPlata t where t.modPlataId = :modPlataId")
    <T> Optional<T> findDTOById(@Param("modPlataId") @NonNull Integer modPlataId, Class<T> type);
}
