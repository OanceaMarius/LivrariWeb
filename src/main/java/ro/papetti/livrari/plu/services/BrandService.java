package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.BrandDto;
import ro.papetti.pluriva.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService extends BaseService<Brand, Integer> {
    <T> List<T> findDTOIAll(Class<T> type);

    <T> Optional<T> findDTOIById(int brandId, Class<T> type);


    Optional<BrandDto> findDtoById(int brandId);

    List<BrandDto> findDtoAll();
}
