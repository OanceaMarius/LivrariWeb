package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipDocRepozitory;
import ro.papetti.livrari.plu.services.TipDocService;
import ro.papetti.pluriva.entity.TipDoc;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class TipDocServiceImpl extends BaseServiceImpl<TipDoc, TipDocRepozitory> implements TipDocService {
    public TipDocServiceImpl(TipDocRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.TIP_DOC_DTO,key = "#tipDocId")
    public <T> Optional<T> findDTOById(int tipDocId, Class<T> type) {
        return rep.findDTOById(tipDocId,type);
    }

}
