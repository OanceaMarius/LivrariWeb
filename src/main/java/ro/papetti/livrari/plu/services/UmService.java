package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Um;

import java.util.List;
import java.util.Optional;

public interface UmService extends BaseService<Um, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int umId, Class<T> type);
}
