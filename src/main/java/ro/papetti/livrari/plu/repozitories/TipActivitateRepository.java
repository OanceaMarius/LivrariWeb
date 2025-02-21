package ro.papetti.livrari.plu.repozitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.TipActivitate;

@Repository
public interface TipActivitateRepository extends JpaRepository<TipActivitate, Integer >{

}