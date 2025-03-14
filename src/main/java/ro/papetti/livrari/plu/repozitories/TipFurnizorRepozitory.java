package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.entity.TipFurnizor;
import ro.papetti.pluriva.model.TblTipFurnizorPK;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TipFurnizorRepozitory extends JpaRepository<TipFurnizor, TblTipFurnizorPK> {



}
