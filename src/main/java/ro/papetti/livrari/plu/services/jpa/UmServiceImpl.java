package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.UmRepozitory;
import ro.papetti.livrari.plu.services.UmService;
import ro.papetti.pluriva.entity.Um;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class UmServiceImpl extends BaseServiceImpl<Um, UmRepozitory> implements UmService {
    public UmServiceImpl(UmRepozitory repozitory) {
        super(repozitory);
    }



    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.UM_DTO,key = "#umId")
    public <T> Optional<T> findDTOById(int umId, Class<T> type) {
        return rep.findDTOById(umId,type);
    }
}
