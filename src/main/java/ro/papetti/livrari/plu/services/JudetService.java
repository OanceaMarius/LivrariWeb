package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Judet;

import java.util.List;
import java.util.Optional;

public interface JudetService extends BaseService<Judet, Integer> {
    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int judetID, Class<T> type);
}
