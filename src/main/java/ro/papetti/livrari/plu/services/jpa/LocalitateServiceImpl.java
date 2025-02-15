package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.LocalitateRepozitory;
import ro.papetti.livrari.plu.services.LocalitateService;
import ro.papetti.pluriva.entity.Localitate;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class LocalitateServiceImpl extends BaseServiceImpl<Localitate, LocalitateRepozitory>
        implements LocalitateService {
    public LocalitateServiceImpl(LocalitateRepozitory repozitory) {
        super(repozitory);
    }


    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.LOCALITATE_DTO,key = "#localitateID")
    public <T> Optional<T> findDTOById(int localitateID, Class<T> type) {
        return rep.findDTOById(localitateID,type);
    }
}
