package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.UnitateRepozitory;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.dto.UnitateDto;
import ro.papetti.pluriva.entity.Unitate;
import ro.papetti.pluriva.mapstruct.UnitateMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class UnitateServiceImpl extends BaseServiceImpl<Unitate, UnitateRepozitory> implements UnitateService {
    public UnitateServiceImpl(UnitateRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private UnitateMapStruct unitateMapStruct;
    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override

    public <T> Optional<T> findDTOIById(int unitateID, Class<T> type) {
        return rep.findDTOIById(unitateID,type);
    }

    @Override
    public List<Unitate> findByDenumireUnitate(String denumireUnitate) {
        return rep.findByDenumireUnitate(denumireUnitate);
    }

    @Override
    public <T> List<T> findDTOByDenumireUnitate(String denumireUnitate, Class<T> type) {
        return rep.findDTOByDenumireUnitate(denumireUnitate,type);
    }

    @Cacheable(cacheNames = CacheName.UNITATE_DTO, key = "#unitateID")
    @Override
    public Optional<UnitateDto> findDtoById(int unitateID){
        return rep.findById(unitateID).map(value-> unitateMapStruct.toDto(value));
    }

    @Override
    public List<UnitateDto> findDtoAll(){
        return unitateMapStruct.toDtoList(rep.findAll());
    }
}
