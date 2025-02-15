package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipStradaRepozitory;
import ro.papetti.livrari.plu.services.TipStradaService;
import ro.papetti.pluriva.entity.TipStrada;

import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TipStradaServiceImpl extends BaseServiceImpl<TipStrada, TipStradaRepozitory> implements TipStradaService {
    public TipStradaServiceImpl(TipStradaRepozitory repozitory) {
        super(repozitory);
    }


    @Cacheable(cacheNames = CacheName.TIP_STRADA_DTO,key = "#tipStradaId")
    public Optional<TipStrada> findById(Integer tipStradaId){
        return  super.findById(tipStradaId);
    }

}
