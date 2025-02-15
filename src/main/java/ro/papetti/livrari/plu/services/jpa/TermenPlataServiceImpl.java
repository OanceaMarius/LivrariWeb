package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TermenPlataRepozitory;
import ro.papetti.livrari.plu.services.TermenPlataService;
import ro.papetti.pluriva.entity.TermenPlata;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class TermenPlataServiceImpl extends BaseServiceImpl<TermenPlata, TermenPlataRepozitory> implements TermenPlataService {
    public TermenPlataServiceImpl(TermenPlataRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.TERMEN_PLATA_DTO,key = "#termenPlataID")
    public <T> Optional<T> findDTOById(int termenPlataID, Class<T> type) {
        return rep.findDTOById(termenPlataID,type);
    }

}
