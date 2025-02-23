package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.ProdusDto;
import ro.papetti.pluriva.entity.Produs;

import java.util.List;
import java.util.Optional;

public interface ProdusService extends BaseService<Produs, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int produsId, Class<T> type);

    Optional<ProdusDto> findDtoById(int produsId);

    Optional<Produs> findById(int produsId);

    List<ProdusDto> findDtoAll();
}
