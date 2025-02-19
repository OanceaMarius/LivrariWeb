package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.BrandRepozitory;
import ro.papetti.livrari.plu.services.BrandService;
import ro.papetti.pluriva.dto.BrandDto;
import ro.papetti.pluriva.entity.Brand;
import ro.papetti.pluriva.mapstruct.BrandMapStruct;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class BrandServiceImpl extends BaseServiceImpl<Brand, BrandRepozitory> implements BrandService {
    public BrandServiceImpl(BrandRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private BrandMapStruct brandMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override

    public <T> Optional<T> findDTOIById(int brandId, Class<T> type) {
        return rep.findDTOIById(brandId,type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.BRAND_DTO,key = "#brandId")
    public Optional<BrandDto> findDtoById(int brandId) {
        Optional<Brand> brand = rep.findById(brandId);
        return brand.map( value -> brandMapStruct.toDto(value));
    }

    @Override
    public List<BrandDto> findDtoAll() {
        List<Brand> brandList = rep.findAll();
        return brandMapStruct.toDtoList(brandList);
    }


}
