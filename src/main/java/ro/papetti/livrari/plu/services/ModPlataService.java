package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.ModPlataDto;
import ro.papetti.pluriva.entity.ModPlata;

import java.util.List;
import java.util.Optional;

public interface ModPlataService extends BaseService<ModPlata, Integer> {

    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int modPlataId, Class<T> type);

    @Cacheable(cacheNames = CacheName.MOD_PLATA_DTO, key = "#modPlataId")
    Optional<ModPlataDto> findDtoById(int modPlataId);

    List<ModPlataDto> findDtoAll();
}
