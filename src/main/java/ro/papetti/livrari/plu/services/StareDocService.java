package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.StareDocDto;
import ro.papetti.pluriva.entity.StareDoc;

import java.util.List;
import java.util.Optional;

public interface StareDocService extends BaseService<StareDoc, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int stareId, Class<T> type);

    @Cacheable(cacheNames = CacheName.STARE_DOC_DTO,key = "#stareId")
    Optional<StareDocDto> findDtoById(int stareId);

    List<StareDocDto> findDtoAll();
}
