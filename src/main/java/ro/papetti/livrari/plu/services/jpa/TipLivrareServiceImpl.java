package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipLivrareRepozitory;
import ro.papetti.livrari.plu.services.TipLivrareService;
import ro.papetti.pluriva.dto.TipLivrareDto;
import ro.papetti.pluriva.entity.TipLivrare;
import ro.papetti.pluriva.mapstruct.TipLivrareMapStruct;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class TipLivrareServiceImpl extends BaseServiceImpl<TipLivrare, TipLivrareRepozitory> implements TipLivrareService {
    public TipLivrareServiceImpl(TipLivrareRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private TipLivrareMapStruct tipLivrareMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override
    public <T> Optional<T> findDTOIById(int tipLivrareId, Class<T> type) {
        return rep.findDTOIById(tipLivrareId,type);
    }

    @Cacheable(cacheNames = CacheName.TIP_LIVRARE_DTO, key = "#tipLivrareId")
    @Override
    public Optional<TipLivrareDto>findDtoById(int tipLivrareId){
        return rep.findById(tipLivrareId).map(value->tipLivrareMapStruct.toDto(value));
    }


    @Override
    public List<TipLivrareDto>findDtoAll(){
        return tipLivrareMapStruct.toDtoList(rep.findAll());
    }
}
