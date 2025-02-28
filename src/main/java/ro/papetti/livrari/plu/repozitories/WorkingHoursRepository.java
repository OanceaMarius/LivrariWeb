package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.WorkingHours;

import java.util.List;
import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Integer> {

  @Query(value = "select w From WorkingHours w where w.workingHoursId = :workingHoursId")
  @EntityGraph(value = "WorkingHours.complet")
  public Optional<WorkingHours> findEagerById(int workingHoursId);

  @Query(value = "select w from WorkingHours w")
  @EntityGraph(value = "WorkingHours.complet")
  public List<WorkingHours> findEagerAll();
}