package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TermenPlataRepozitory;
import ro.papetti.livrari.plu.services.TermenPlataService;
import ro.papetti.pluriva.dto.TermenPlataDto;
import ro.papetti.pluriva.entity.TermenPlata;
import ro.papetti.pluriva.mapstruct.TermenPlataMapStruct;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class TermenPlataServiceImpl extends BaseServiceImpl<TermenPlata, TermenPlataRepozitory> implements TermenPlataService {
    public TermenPlataServiceImpl(TermenPlataRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private TermenPlataMapStruct termenPlataMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override

    public <T> Optional<T> findDTOIById(int termenPlataID, Class<T> type) {
        return rep.findDTOIById(termenPlataID,type);
    }

    @Cacheable(cacheNames = CacheName.TERMEN_PLATA_DTO, key = "#termenPlataID")
    @Override
    public Optional<TermenPlataDto> findDtoById(int termenPlataID){
        Optional<TermenPlata>termenPlata=rep.findById(termenPlataID);
        return termenPlata.map(value->termenPlataMapStruct.toDto(value));
    }

    @Override
    public List<TermenPlataDto>findDtoAll(){
      return termenPlataMapStruct.toDtoList(rep.findAll());
    };

}
