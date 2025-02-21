package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TvaRepozitory;
import ro.papetti.livrari.plu.services.TvaService;
import ro.papetti.pluriva.dto.TvaDto;
import ro.papetti.pluriva.entity.Tva;
import ro.papetti.pluriva.mapstruct.TvaMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TvaServiceImpl extends BaseServiceImpl<Tva, TvaRepozitory> implements TvaService {
    public TvaServiceImpl(TvaRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private TvaMapStruct tvaMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override
    public <T> Optional<T> findDTOIById(int tvaId, Class<T> type) {
        return rep.findDTOIById(tvaId, type);
    }

    @Cacheable(cacheNames = CacheName.TVA_DTO, key = "#tvaId")
    @Override
    public Optional<TvaDto> findDtoById(int tvaId) {
        return rep.findById(tvaId).map(value -> tvaMapStruct.toDto(value));
    }

    @Override
    public List<TvaDto>findDtoAll(){
        return tvaMapStruct.toDtoList(rep.findAll());
    }

}
