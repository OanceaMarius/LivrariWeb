package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.CpvDto;
import ro.papetti.pluriva.entity.Cpv;

import java.util.List;
import java.util.Optional;

public interface CpvService extends BaseService<Cpv, Integer> {
    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int cPVId, Class<T> type);

    @Cacheable(cacheNames = CacheName.CPV_DTO,key = "#cPVId")
    Optional<CpvDto> findDtoById(int cpvId);

    List<CpvDto> findDtoAll();
}
