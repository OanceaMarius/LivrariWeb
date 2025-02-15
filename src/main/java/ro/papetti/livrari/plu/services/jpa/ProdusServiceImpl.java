package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.ProdusRepozitory;
import ro.papetti.livrari.plu.services.ProdusService;
import ro.papetti.pluriva.entity.Produs;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class ProdusServiceImpl extends BaseServiceImpl<Produs, ProdusRepozitory> implements ProdusService {
    public ProdusServiceImpl(ProdusRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.PRODUS_DTO,key = "#produsId")
    public <T> Optional<T> findDTOById(int produsId, Class<T> type) {
        return rep.findDTOById(produsId,type);
    }
}
