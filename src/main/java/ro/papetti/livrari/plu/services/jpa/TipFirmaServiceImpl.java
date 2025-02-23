package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipFirmaRepozitory;
import ro.papetti.livrari.plu.services.TipFirmaService;
import ro.papetti.pluriva.dto.TipFirmaDto;
import ro.papetti.pluriva.entity.TipFirma;
import ro.papetti.pluriva.mapstruct.TipFirmaMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class TipFirmaServiceImpl extends BaseServiceImpl<TipFirma, TipFirmaRepozitory> implements TipFirmaService {
    public TipFirmaServiceImpl(TipFirmaRepozitory repozitory) {
        super(repozitory);
    }
@Autowired
private TipFirmaMapStruct tipFirmaMapStruct;


    public Optional<TipFirma> findById(Integer tipFirmaId){
        return super.findById(tipFirmaId);
    }

    @Cacheable(cacheNames = CacheName.TIP_FIRMA_DTO, key = "#tipFirmaId")
    @Override
    public Optional<TipFirmaDto> findDtoById(int tipFirmaId){
        Optional<TipFirma>tipFirma=findById(tipFirmaId);
        return tipFirma.map(value-> tipFirmaMapStruct.toDto(value));
    }


    @Override
    public List<TipFirmaDto> findDtoAll(){
        return tipFirmaMapStruct.toDtoList(rep.findAll());
    }
}
