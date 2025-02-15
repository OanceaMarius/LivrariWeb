package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Produs;

import java.util.List;
import java.util.Optional;

public interface ProdusService extends BaseService<Produs, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int produsId, Class<T> type);
}
