package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipFirmaRepozitory;
import ro.papetti.livrari.plu.services.TipFirmaService;
import ro.papetti.pluriva.entity.TipFirma;

import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TipFirmaServiceImpl extends BaseServiceImpl<TipFirma, TipFirmaRepozitory> implements TipFirmaService {
    public TipFirmaServiceImpl(TipFirmaRepozitory repozitory) {
        super(repozitory);
    }



    @Cacheable(cacheNames = CacheName.TIP_FIRMA_DTO,key = "#tipFirmaId")
    public Optional<TipFirma> findById(Integer tipFirmaId){
        return super.findById(tipFirmaId);
    }

}
