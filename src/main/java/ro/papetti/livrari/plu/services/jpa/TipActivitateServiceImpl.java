package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipActivitateRepository;
import ro.papetti.livrari.plu.services.TipActivitateService;
import ro.papetti.pluriva.dto.TipActivitateDto;
import ro.papetti.pluriva.entity.TipActivitate;
import ro.papetti.pluriva.mapstruct.TipActivitateMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TipActivitateServiceImpl extends BaseServiceImpl<TipActivitate, TipActivitateRepository>
        implements TipActivitateService {

    public TipActivitateServiceImpl(TipActivitateRepository repozitory) {
        super(repozitory);
    }
    @Autowired
    private TipActivitateMapStruct tipActivitateMapStruct;

    @Override
    @Cacheable(cacheNames = CacheName.TIP_ACTIVITATE,key = "#tipActivitateID")
    public Optional<TipActivitateDto> findDtoById(int tipActivitateID) {
        Optional<TipActivitate> tipActivitate = rep.findById(tipActivitateID);
        return tipActivitate.map(value ->tipActivitateMapStruct.toDto(value));
    }

    @Override
    public List<TipActivitateDto> findDtoAll() {
        List<TipActivitate>tipActivitateList=rep.findAll();
        return tipActivitateMapStruct.toDtoList(tipActivitateList);
    }
}
