package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.CpvRepozitory;
import ro.papetti.livrari.plu.services.CpvService;
import ro.papetti.pluriva.dto.CpvDto;
import ro.papetti.pluriva.entity.Cpv;
import ro.papetti.pluriva.mapstruct.CpvMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class CpvServiceImpl extends BaseServiceImpl<Cpv, CpvRepozitory> implements CpvService {
    public CpvServiceImpl(CpvRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private CpvMapStruct cpvMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }


    @Override
    public <T> Optional<T> findDTOIById(int cPVId, Class<T> type) {
        return rep.findDTOIById(cPVId,type);
    }


    public Optional<Cpv> findById(int cPVId) {
        return super.findById(cPVId);
    }


    @Cacheable(cacheNames = CacheName.CPV_DTO, key = "#cpvId")
    @Override
    public Optional<CpvDto> findDtoById(int cpvId){
        return findById(cpvId).map(value->cpvMapStruct.toDto(value));
    }

    @Override
    public List<CpvDto> findDtoAll(){
        return cpvMapStruct.toDtoList(super.findAll());
    }




}
