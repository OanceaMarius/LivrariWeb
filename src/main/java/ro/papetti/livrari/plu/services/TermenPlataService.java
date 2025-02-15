package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.TermenPlata;

import java.util.List;
import java.util.Optional;

public interface TermenPlataService extends BaseService<TermenPlata, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int termenPlataID, Class<T> type);
}
