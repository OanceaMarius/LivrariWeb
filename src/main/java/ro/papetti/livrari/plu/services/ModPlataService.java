package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.ModPlata;

import java.util.List;
import java.util.Optional;

public interface ModPlataService extends BaseService<ModPlata, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int modPlataId, Class<T> type);
}
