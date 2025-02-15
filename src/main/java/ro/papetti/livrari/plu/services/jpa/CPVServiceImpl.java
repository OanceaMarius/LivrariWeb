package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.CPVRepozitory;
import ro.papetti.livrari.plu.services.CPVService;
import ro.papetti.pluriva.entity.CPV;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class CPVServiceImpl extends BaseServiceImpl<CPV, CPVRepozitory> implements CPVService {
    public CPVServiceImpl(CPVRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }


    @Override
    @Cacheable(cacheNames = CacheName.CPV_DTO,key = "#cPVId")
    public <T> Optional<T> findDTOById(int cPVId, Class<T> type) {
        return rep.findDTOById(cPVId,type);
    }

    //Neavand DTO iau cache-ul normal de acolo
    @Cacheable(cacheNames = CacheName.CPV_DTO,key = "#cPVId")
    public Optional<CPV> findById(int cPVId) {
        return super.findById(cPVId);
    }


}
