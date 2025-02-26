package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.Lead;

import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface LeadRepository extends JpaRepository<Lead, Integer>, JpaSpecificationExecutor<Lead> {

    @Query(value = "select l from Lead l where l.leadId = :leadId")
    @EntityGraph(value = "Lead.complet")
    public Optional<Lead> findEagerById(@NonNull int leadId);
}