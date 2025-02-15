package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.TipLivrare;

import java.util.List;
import java.util.Optional;

public interface TipLivrareService extends BaseService<TipLivrare, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int tipLivrareId, Class<T> type);
}
