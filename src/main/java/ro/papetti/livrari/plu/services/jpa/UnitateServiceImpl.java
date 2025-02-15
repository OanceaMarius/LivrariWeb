package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.UnitateRepozitory;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.entity.Unitate;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class UnitateServiceImpl extends BaseServiceImpl<Unitate, UnitateRepozitory> implements UnitateService {
    public UnitateServiceImpl(UnitateRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.UNITATE_DTO,key = "#unitateID")
    public <T> Optional<T> findDTOById(int unitateID, Class<T> type) {
        return rep.findDTOById(unitateID,type);
    }

    @Override
    public List<Unitate> findByDenumireUnitate(String denumireUnitate) {
        return rep.findByDenumireUnitate(denumireUnitate);
    }

    @Override
    public <T> List<T> findDTOByDenumireUnitate(String denumireUnitate, Class<T> type) {
        return rep.findDTOByDenumireUnitate(denumireUnitate,type);
    }
}
