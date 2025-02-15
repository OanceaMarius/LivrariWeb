package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Tva;

import java.util.List;
import java.util.Optional;

public interface TvaService extends BaseService<Tva, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int tvaId, Class<T> type);


}
