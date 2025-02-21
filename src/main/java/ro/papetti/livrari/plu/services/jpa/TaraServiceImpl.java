package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TaraRepozitory;
import ro.papetti.livrari.plu.services.TaraService;
import ro.papetti.pluriva.dto.TaraDto;
import ro.papetti.pluriva.entity.Tara;
import ro.papetti.pluriva.mapstruct.TaraMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TaraServiceImpl extends BaseServiceImpl<Tara, TaraRepozitory>implements TaraService {
    public TaraServiceImpl(TaraRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private TaraMapStruct taraMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override

    public <T> Optional<T> findDTOIById(int taraID, Class<T> type) {
        return rep.findDTOIById(taraID,type);
    }

    @Cacheable(cacheNames = CacheName.TARA_DTO, key = "#taraID")
    @Override
    public Optional<TaraDto> findDtoById(int taraID){
        Optional<Tara> tara = rep.findById(taraID);
        return tara.map(value->taraMapStruct.toDto(value));
    };

    @Override
    public List<TaraDto> findDtoAll(){
        return taraMapStruct.toDtoList(rep.findAll());
    }

}
