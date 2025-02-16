package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Lead;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface LeadRepository extends JpaRepository<Lead, Integer>, JpaSpecificationExecutor<Lead> {

}