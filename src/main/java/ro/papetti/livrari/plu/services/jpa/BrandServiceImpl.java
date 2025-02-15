package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.BrandRepozitory;
import ro.papetti.livrari.plu.services.BrandService;
import ro.papetti.pluriva.entity.Brand;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class BrandServiceImpl extends BaseServiceImpl<Brand, BrandRepozitory> implements BrandService {
    public BrandServiceImpl(BrandRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.BRAND_DTO,key = "#brandId")
    public <T> Optional<T> findDTOById(int brandId, Class<T> type) {
        return rep.findDTOById(brandId,type);
    }


}
