package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.JudetRepozitory;
import ro.papetti.livrari.plu.services.JudetService;
import ro.papetti.pluriva.entity.Judet;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class JudetServiceImpl extends BaseServiceImpl<Judet, JudetRepozitory>
        implements JudetService {
    public JudetServiceImpl(JudetRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable(cacheNames = CacheName.JUDET_DTO,key = "#judetID")
    public <T> Optional<T> findDTOById(int judetID, Class<T> type) {
        return rep.findDTOById(judetID,type);
    }

}
