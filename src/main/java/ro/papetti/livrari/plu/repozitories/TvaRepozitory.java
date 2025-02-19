package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Tva;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TvaRepozitory extends JpaRepository<Tva, Integer> {

    @Query("SELECT t FROM Tva t ")
    public <T> List<T> findDTOAll(Class<T> type);

    @Query("select t from Tva t where t.tvaId = :tvaId")
    <T> Optional<T> findDTOById(@Param("tvaId") @NonNull Integer tvaId, Class<T> type);


}
