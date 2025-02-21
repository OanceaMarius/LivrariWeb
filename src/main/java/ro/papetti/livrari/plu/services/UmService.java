package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.UmDto;
import ro.papetti.pluriva.entity.Um;

import java.util.List;
import java.util.Optional;

public interface UmService extends BaseService<Um, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int uMId, Class<T> type);

    Optional<UmDto>findDtoById(int umId);

    List<UmDto>findDtoAll();
}
