package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Localitate;

import java.util.List;
import java.util.Optional;

public interface LocalitateService extends BaseService<Localitate, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int localitateID, Class<T> type);
}
