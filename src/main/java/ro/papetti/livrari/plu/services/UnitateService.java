package ro.papetti.livrari.plu.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Unitate;

import java.util.List;
import java.util.Optional;

public interface UnitateService extends BaseService<Unitate, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int unitateID, Class<T> type);

    List<Unitate> findByDenumireUnitate(String denumireUnitate);

    <T> List<T> findDTOByDenumireUnitate(String denumireUnitate, Class<T> type);



}
