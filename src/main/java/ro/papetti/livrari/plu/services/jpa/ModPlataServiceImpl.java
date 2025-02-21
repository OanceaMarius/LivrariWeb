package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.ModPlataRepozitory;
import ro.papetti.livrari.plu.services.ModPlataService;
import ro.papetti.pluriva.dto.ModPlataDto;
import ro.papetti.pluriva.entity.ModPlata;
import ro.papetti.pluriva.mapstruct.ModPlataMapStruct;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class ModPlataServiceImpl extends BaseServiceImpl<ModPlata, ModPlataRepozitory>
                        implements ModPlataService
{
    public ModPlataServiceImpl(ModPlataRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private ModPlataMapStruct modPlataMapStruct;

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.MOD_PLATA_DTO,key = "#modPlataId")
    public <T> Optional<T> findDTOById(int modPlataId, Class<T> type) {
        return rep.findDTOById(modPlataId,type);
    }

    @Cacheable(cacheNames = CacheName.MOD_PLATA_DTO, key = "#modPlataId")
    @Override
    public Optional<ModPlataDto> findDtoById(int modPlataId){
        Optional<ModPlata> modPlata = rep.findById(modPlataId);
      return modPlata.map(value -> modPlataMapStruct.toDto(value));
    };

    @Override
    public List<ModPlataDto> findDtoAll(){
       return modPlataMapStruct.toDtoList(rep.findAll());
    };



}
