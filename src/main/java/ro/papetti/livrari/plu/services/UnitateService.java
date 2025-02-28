package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.UnitateDto;
import ro.papetti.pluriva.entity.Unitate;

import java.util.List;
import java.util.Optional;

public interface UnitateService extends BaseService<Unitate, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int unitateID, Class<T> type);

    List<Unitate> findByDenumireUnitate(String denumireUnitate);

    <T> List<T> findDTOByDenumireUnitate(String denumireUnitate, Class<T> type);

    Optional<UnitateDto> findDtoById(Integer unitateID);

    List<UnitateDto> findDtoAll();

    Optional<Unitate> findById(int unitateID);

    Optional<Unitate> findEagerByUnitateID(int unitateID);
}
