package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.TermenPlataDto;
import ro.papetti.pluriva.entity.TermenPlata;

import java.util.List;
import java.util.Optional;

public interface TermenPlataService extends BaseService<TermenPlata, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int termenPlataID, Class<T> type);

    @Cacheable(cacheNames = CacheName.TERMEN_PLATA_DTO,key = "#termenPlataID")
    Optional<TermenPlataDto> findDtoById(int termenPlataID);

    List<TermenPlataDto>findDtoAll();
}
