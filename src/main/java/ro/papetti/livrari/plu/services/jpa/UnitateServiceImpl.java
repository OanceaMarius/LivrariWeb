package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.UnitateRepozitory;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;
import ro.papetti.pluriva.entity.Unitate;
import ro.papetti.pluriva.mapstruct.UnitateMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class UnitateServiceImpl extends BaseServiceImpl<Unitate, UnitateRepozitory> implements UnitateService {
    public UnitateServiceImpl(UnitateRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private UnitateMapStruct unitateMapStruct;
    @Autowired
    private CompletareDtoService completareDtoService;
    @Autowired
    private PartenerService partenerService;
    @Autowired
    private ContactService contactService;


    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override

    public <T> Optional<T> findDTOIById(int unitateID, Class<T> type) {
        return rep.findDTOIById(unitateID,type);
    }

    @Override
    public List<Unitate> findByDenumireUnitate(String denumireUnitate) {
        return rep.findByDenumireUnitate(denumireUnitate);
    }

    @Override
    public <T> List<T> findDTOByDenumireUnitate(String denumireUnitate, Class<T> type) {
        return rep.findDTOByDenumireUnitate(denumireUnitate,type);
    }

    @Cacheable(cacheNames = CacheName.UNITATE_DTO, key = "#unitateID",condition = "#unitateID != null")
    @Override
    public Optional<UnitateDto> findDtoById(Integer unitateID){
        if (unitateID==null)
            return Optional.empty();

        Optional<UnitateDto>unitateDto= rep.findById(unitateID).map(value-> unitateMapStruct.toDto(value));
        if (unitateDto.isEmpty())
            return Optional.empty();

        setUnitateDtoFromCache(unitateDto.get());
        return unitateDto;
    }

    @Override
    public List<UnitateDto> findDtoAll(){
        List<UnitateDto>unitateDtoList = unitateMapStruct.toDtoList(rep.findAll());
        for (UnitateDto unitateDto:unitateDtoList){
            setUnitateDtoFromCache(unitateDto);
        }
        return unitateDtoList;
    }


    @Override
    public Optional<Unitate> findById(int unitateID){
        return rep.findById(unitateID);
    }

    @Override
    public Optional<Unitate> findEagerByUnitateID(int unitateID){
        return rep.findEagerByUnitateID(unitateID);
    }

    private void setUnitateDtoFromCache(UnitateDto unitateDto){
        unitateDto.setTaraDto(completareDtoService.getTaraDtoById(unitateDto.getTaraId()));
        unitateDto.setJudetDto(completareDtoService.getJudetDtoById(unitateDto.getJudetId()));
        unitateDto.setLocalitateDto(completareDtoService.getLocalitateDtoById(unitateDto.getLocalitateId()));
        unitateDto.setTipStradaDto(completareDtoService.getTipStradaDtoById(unitateDto.getTipStradaId()));
        unitateDto.setPartenerDto(partenerService.findDtoById(unitateDto.getPartenerID()).orElse(null));
        unitateDto.setUserIntroducereDto(completareDtoService.getUserDtoById(unitateDto.getUserIntroducereId()));
        unitateDto.setUserModificareDto(completareDtoService.getUserDtoById(unitateDto.getUserModificareId()));
        unitateDto.setWorkingHoursDto(completareDtoService.getWorkingHoursDtoById(unitateDto.getWorkingHoursId()));
        unitateDto.setContactDtoSet(contactService.findContacteDtoByUnitateId(unitateDto.getUnitateID()));
    }
}
