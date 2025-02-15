package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.TipDoc;

import java.util.List;
import java.util.Optional;

public interface TipDocService extends BaseService<TipDoc, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int tipDocId, Class<T> type);
}
