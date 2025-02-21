package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.StareDocRepozitory;
import ro.papetti.livrari.plu.services.StareDocService;
import ro.papetti.pluriva.dto.StareDocDto;
import ro.papetti.pluriva.entity.StareDoc;
import ro.papetti.pluriva.mapstruct.StareDocMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class StareDocServiceImpl extends BaseServiceImpl<StareDoc, StareDocRepozitory> implements StareDocService {
    public StareDocServiceImpl(StareDocRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private StareDocMapStruct stareDocMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override
    public <T> Optional<T> findDTOIById(int stareId, Class<T> type) {
        return rep.findDTOIById(stareId,type);
    }

    @Cacheable(cacheNames = CacheName.STARE_DOC_DTO, key = "#stareId")
    @Override
    public Optional<StareDocDto> findDtoById(int stareId){
        Optional<StareDoc> stareDoc = rep.findById(stareId);
        return stareDoc.map(value-> stareDocMapStruct.toDto(value));
    };

    @Override
    public List<StareDocDto> findDtoAll(){
      return stareDocMapStruct.toDtoList(rep.findAll());
    };

}
