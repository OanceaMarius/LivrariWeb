package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipLivrareRepozitory;
import ro.papetti.livrari.plu.services.TipLivrareService;
import ro.papetti.pluriva.entity.TipLivrare;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class TipLivrareServiceImpl extends BaseServiceImpl<TipLivrare, TipLivrareRepozitory> implements TipLivrareService {
    public TipLivrareServiceImpl(TipLivrareRepozitory repozitory) {
        super(repozitory);
    }



    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.TIP_LIVRARE_DTO,key = "#tipLivrareId")
    public <T> Optional<T> findDTOById(int tipLivrareId, Class<T> type) {
        return rep.findDTOById(tipLivrareId,type);
    }

}
