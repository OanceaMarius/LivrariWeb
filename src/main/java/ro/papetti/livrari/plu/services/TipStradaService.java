package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.TipStradaDto;
import ro.papetti.pluriva.entity.TipStrada;

import java.util.List;
import java.util.Optional;

public interface TipStradaService extends BaseService<TipStrada, Integer> {
    @Cacheable(cacheNames = CacheName.TIP_STRADA_DTO,key = "#tipStradaId")
    Optional<TipStradaDto> findDtoById(int tipStradaId);

    List<TipStradaDto> findDtoAll();
}
