package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.TipLivrareDto;
import ro.papetti.pluriva.entity.TipLivrare;

import java.util.List;
import java.util.Optional;

public interface TipLivrareService extends BaseService<TipLivrare, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int tipLivrareId, Class<T> type);

    @Cacheable(cacheNames = CacheName.TIP_LIVRARE_DTO,key = "#tipLivrareId")
    Optional<TipLivrareDto>findDtoById(int tipLivrareId);

    List<TipLivrareDto>findDtoAll();
}
