package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService extends BaseService<Brand, Integer> {
    <T> List<T> findDTOAll(Class<T> type);

    <T> Optional<T> findDTOById(int brandId, Class<T> type);
}
