package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.LocalitateDto;
import ro.papetti.pluriva.entity.Localitate;

import java.util.List;
import java.util.Optional;

public interface LocalitateService extends BaseService<Localitate, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int localitateID, Class<T> type);

    Optional<LocalitateDto> findDtoById(int localitateID);

    List<LocalitateDto> findDtoAll();
}
