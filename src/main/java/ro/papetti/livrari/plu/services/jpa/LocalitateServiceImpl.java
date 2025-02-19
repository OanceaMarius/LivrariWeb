package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.LocalitateRepozitory;
import ro.papetti.livrari.plu.services.LocalitateService;
import ro.papetti.pluriva.dto.LocalitateDto;
import ro.papetti.pluriva.entity.Localitate;
import ro.papetti.pluriva.mapstruct.LocalitateMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class LocalitateServiceImpl extends BaseServiceImpl<Localitate, LocalitateRepozitory>
        implements LocalitateService {
    public LocalitateServiceImpl(LocalitateRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private LocalitateMapStruct localitateMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    public <T> Optional<T> findDTOIById(int localitateID, Class<T> type) {
        return rep.findDTOById(localitateID,type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.LOCALITATE_DTO,key = "#localitateID")
    public Optional<LocalitateDto> findDtoById(int localitateID) {
        Optional<Localitate> localitate = rep.findById(localitateID);
        return localitate.map(value -> localitateMapStruct.toDto(value));
    }

    @Override
    public List<LocalitateDto> findDtoAll() {
        List<Localitate> localitateList = rep.findAll();
        return localitateMapStruct.toDtoList(localitateList);
    }


}
