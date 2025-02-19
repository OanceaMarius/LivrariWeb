package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.JudetDto;
import ro.papetti.pluriva.entity.Judet;

import java.util.List;
import java.util.Optional;

public interface JudetService extends BaseService<Judet, Integer> {
    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int judetID, Class<T> type);

    @Cacheable(cacheNames = CacheName.JUDET_DTO,key = "#judetID")
    Optional<JudetDto> findDtoById(int judetID);

    List<JudetDto> findDtoAll();
}
