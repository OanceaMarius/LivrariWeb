package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.StareDoc;

import java.util.List;
import java.util.Optional;

public interface StareDocService extends BaseService<StareDoc, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int stareId, Class<T> type);
}
