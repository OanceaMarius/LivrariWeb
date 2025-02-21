package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.TipDocDto;
import ro.papetti.pluriva.entity.TipDoc;

import java.util.List;
import java.util.Optional;

public interface TipDocService extends BaseService<TipDoc, Integer> {

    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int tipDocId, Class<T> type);

    @Cacheable(cacheNames = CacheName.TIP_DOC_DTO,key = "#tipDocId")
    Optional<TipDocDto>findDtoById(int tipDocId);

    List<TipDocDto>findDtoAll();
}
