package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.JudetRepozitory;
import ro.papetti.livrari.plu.services.JudetService;
import ro.papetti.pluriva.dto.JudetDto;
import ro.papetti.pluriva.entity.Judet;
import ro.papetti.pluriva.mapstruct.JudetMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class JudetServiceImpl extends BaseServiceImpl<Judet, JudetRepozitory>
        implements JudetService {
    public JudetServiceImpl(JudetRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private JudetMapStruct judetMapStruct;


    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    public <T> Optional<T> findDTOIById(int judetID, Class<T> type) {
        return rep.findDTOById(judetID,type);
    }

    @Cacheable(cacheNames = CacheName.JUDET_DTO, key = "#judetID")
    @Override
    public Optional<JudetDto> findDtoById(int judetID) {
        Optional<Judet> judet = rep.findById(judetID);
        return judet.map(value -> judetMapStruct.toDto(value));
    }

    @Override
    public List<JudetDto> findDtoAll(){
        List<Judet> judetList = rep.findAll();
        return judetMapStruct.toDtoList(judetList);
    }

}
