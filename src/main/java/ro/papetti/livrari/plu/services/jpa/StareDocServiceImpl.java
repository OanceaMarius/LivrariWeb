package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.StareDocRepozitory;
import ro.papetti.livrari.plu.services.StareDocService;
import ro.papetti.pluriva.entity.StareDoc;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class StareDocServiceImpl extends BaseServiceImpl<StareDoc, StareDocRepozitory> implements StareDocService {
    public StareDocServiceImpl(StareDocRepozitory repozitory) {
        super(repozitory);
    }


    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.STARE_DOC_DTO,key = "#stareId")
    public <T> Optional<T> findDTOById(int stareId, Class<T> type) {
        return rep.findDTOById(stareId,type);
    }

}
