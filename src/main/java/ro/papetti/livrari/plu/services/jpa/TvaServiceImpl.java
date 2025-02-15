package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TvaRepozitory;
import ro.papetti.livrari.plu.services.TvaService;
import ro.papetti.pluriva.entity.Tva;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TvaServiceImpl extends BaseServiceImpl<Tva, TvaRepozitory> implements TvaService {
    public TvaServiceImpl(TvaRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.TVA_DTO,key = "#tvaId")
    public <T> Optional<T> findDTOById(int tvaId, Class<T> type) {
        return rep.findDTOById(tvaId, type);
    }
}
