package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.GestiuneRepozitory;
import ro.papetti.livrari.plu.services.GestiuneService;
import ro.papetti.pluriva.dto.GestiuneDto;
import ro.papetti.pluriva.entity.Gestiune;
import ro.papetti.pluriva.mapstruct.GestiuneMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class GestiuneServiceImpl extends BaseServiceImpl<Gestiune, GestiuneRepozitory> implements GestiuneService {
    public GestiuneServiceImpl(GestiuneRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private GestiuneMapStruct gestiuneMapStruct;

    @Cacheable(cacheNames = CacheName.GESTIUNE, key = "#gestiuneId")
    @Override
    public Optional<GestiuneDto> findDtoById(int gestiuneId) {
        Optional<Gestiune> gestiune = findById(gestiuneId);
        return gestiune.map(value-> gestiuneMapStruct.toDto(value));
    }


    @Override
    public List<GestiuneDto> findDtoAll(){
        List<Gestiune> gestiuneList = findAll();
        return gestiuneMapStruct.toDtoList(gestiuneList);
    }
}
