package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.TipFirmaDto;
import ro.papetti.pluriva.entity.TipFirma;

import java.util.List;
import java.util.Optional;

public interface TipFirmaService extends BaseService<TipFirma, Integer> {
    @Cacheable(cacheNames = CacheName.TIP_FIRMA_DTO,key = "#tipFirmaId")
    Optional<TipFirmaDto> findDtoById(int tipFirmaId);

    List<TipFirmaDto> findDtoAll();
}
