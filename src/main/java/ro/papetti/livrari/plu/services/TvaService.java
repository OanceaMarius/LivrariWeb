package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.TvaDto;
import ro.papetti.pluriva.entity.Tva;

import java.util.List;
import java.util.Optional;

public interface TvaService extends BaseService<Tva, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int tvaId, Class<T> type);


    @Cacheable(cacheNames = CacheName.TVA_DTO, key = "#tvaId")
    Optional<TvaDto> findDtoById(int tvaId);

    List<TvaDto>findDtoAll();
}
