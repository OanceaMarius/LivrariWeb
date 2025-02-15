package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Tara;

import java.util.List;
import java.util.Optional;

public interface TaraService extends BaseService<Tara, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int taraID, Class<T> type);

}
