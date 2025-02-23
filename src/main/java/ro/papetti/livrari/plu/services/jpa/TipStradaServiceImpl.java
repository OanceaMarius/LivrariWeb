package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipStradaRepozitory;
import ro.papetti.livrari.plu.services.TipStradaService;
import ro.papetti.pluriva.dto.TipStradaDto;
import ro.papetti.pluriva.entity.TipStrada;
import ro.papetti.pluriva.mapstruct.TipStradaMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TipStradaServiceImpl extends BaseServiceImpl<TipStrada, TipStradaRepozitory> implements TipStradaService {
    public TipStradaServiceImpl(TipStradaRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private TipStradaMapStruct tipStradaMapStruct;


    public Optional<TipStrada> findById(Integer tipStradaId){
        return  super.findById(tipStradaId);
    }

    @Cacheable(cacheNames = CacheName.TIP_STRADA_DTO, key = "#tipStradaId")
    @Override
    public Optional<TipStradaDto> findDtoById(int tipStradaId){
        Optional<TipStrada>tipStrada = findById(tipStradaId);
        return tipStrada.map(value->tipStradaMapStruct.toDto(value));
    }

    @Override
    public List<TipStradaDto> findDtoAll(){
        return tipStradaMapStruct.toDtoList(rep.findAll());
    }
}
