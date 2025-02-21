package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.UmRepozitory;
import ro.papetti.livrari.plu.services.UmService;
import ro.papetti.pluriva.dto.UmDto;
import ro.papetti.pluriva.entity.Um;
import ro.papetti.pluriva.mapstruct.UmMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class UmServiceImpl extends BaseServiceImpl<Um, UmRepozitory> implements UmService {
    public UmServiceImpl(UmRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private UmMapStruct umMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override
    public <T> Optional<T> findDTOIById(int umId, Class<T> type) {
        return rep.findDTOIById(umId,type);
    }

    @Cacheable(cacheNames = CacheName.UM_DTO, key = "#umId")
    @Override
    public Optional<UmDto>findDtoById(int umId){
        return rep.findById(umId).map(value->umMapStruct.toDto(value));
    }


    @Override
    public List<UmDto>findDtoAll(){
        return umMapStruct.toDtoList(rep.findAll());
    }
}
