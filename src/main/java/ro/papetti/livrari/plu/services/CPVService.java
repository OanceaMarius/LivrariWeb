package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.CPV;

import java.util.List;
import java.util.Optional;

public interface CPVService extends BaseService<CPV, Integer> {
    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int cPVId, Class<T> type);
}
