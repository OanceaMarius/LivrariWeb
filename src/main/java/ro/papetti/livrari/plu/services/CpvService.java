package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Cpv;

import java.util.List;
import java.util.Optional;

public interface CpvService extends BaseService<Cpv, Integer> {
    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int cPVId, Class<T> type);
}
