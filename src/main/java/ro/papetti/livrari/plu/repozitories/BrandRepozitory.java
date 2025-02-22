package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.dto.BrandDto;
import ro.papetti.pluriva.entity.Brand;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface BrandRepozitory extends JpaRepository<Brand, Integer> {

    @Query("SELECT t FROM Brand t ")
     <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from Brand t where t.brandId = :brandId")
    <T> Optional<T> findDTOIById(@Param("brandId") @NonNull Integer brandId, Class<T> type);


}
