package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.ModPlataRepozitory;
import ro.papetti.livrari.plu.services.ModPlataService;
import ro.papetti.pluriva.entity.ModPlata;

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


    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.MOD_PLATA_DTO,key = "#modPlataId")
    public <T> Optional<T> findDTOById(int modPlataId, Class<T> type) {
        return rep.findDTOById(modPlataId,type);
    }


}
