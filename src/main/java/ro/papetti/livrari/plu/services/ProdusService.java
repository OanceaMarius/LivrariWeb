package ro.papetti.livrari.plu.services;

import org.springframework.data.domain.Page;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.ProdusDto;
import ro.papetti.pluriva.entity.Produs;

import java.util.List;
import java.util.Optional;

public interface ProdusService extends BaseService<Produs, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int produsId, Class<T> type);

    Optional<ProdusDto> findDtoById(int produsId);

    Optional<Produs> findEagerById(int produsId);

    Optional<Produs> findById(int produsID);

    List<ProdusDto> findDtoAll();
}
