package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.TaraDto;
import ro.papetti.pluriva.entity.Tara;

import java.util.List;
import java.util.Optional;

public interface TaraService extends BaseService<Tara, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int taraID, Class<T> type);

    @Cacheable(cacheNames = CacheName.TARA_DTO,key = "#taraID")
    Optional<TaraDto> findDtoById(int taraID);

    List<TaraDto> findDtoAll();
}
