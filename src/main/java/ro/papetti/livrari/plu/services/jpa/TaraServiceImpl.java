package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TaraRepozitory;
import ro.papetti.livrari.plu.services.TaraService;
import ro.papetti.pluriva.entity.Tara;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TaraServiceImpl extends BaseServiceImpl<Tara, TaraRepozitory>implements TaraService {
    public TaraServiceImpl(TaraRepozitory repozitory) {
        super(repozitory);
    }


    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.TARA_DTO,key = "#taraID")
    public <T> Optional<T> findDTOById(int taraID, Class<T> type) {
        return rep.findDTOById(taraID,type);
    }

}
