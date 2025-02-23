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
    private TaraService taraService;
    @Autowired
    private JudetService judetService;
    @Autowired
    private LocalitateService localitateService;
    @Autowired
    private PartenerService  partenerService;
    @Autowired
    private UserService userService;
    @Autowired
    private TipStradaService tipStradaService;






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

    @Cacheable(cacheNames = CacheName.UNITATE_DTO, key = "#unitateID")
    @Override
    public Optional<UnitateDto> findDtoById(int unitateID){
        Optional<UnitateDto>unitateDto= rep.findById(unitateID).map(value-> unitateMapStruct.toDto(value));
        if (unitateDto.isEmpty())
            return Optional.empty();

        setDtoFromCache(unitateDto.get());
        return unitateDto;
    }

    @Override
    public List<UnitateDto> findDtoAll(){
        List<UnitateDto>unitateDtoList = unitateMapStruct.toDtoList(rep.findAll());
        for (UnitateDto unitateDto:unitateDtoList){
            setDtoFromCache(unitateDto);
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

    private void setTaraDtoFromCache(UnitateDto unitateDto) {
        Integer taraId = unitateDto.getTaraId();
        if (taraId == null || unitateDto.getTaraDto() != null) {
            System.out.println("NU pun DTO din cache pt. taraId: " + taraId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<TaraDto> taraDto = taraService.findDtoById(taraId);
        taraDto.ifPresent(unitateDto::setTaraDto);
    }

    private void setJudetDtoFromCache(UnitateDto unitateDto) {
        Integer judetId = unitateDto.getJudetId();
        if (judetId == null || unitateDto.getJudetDto() != null) {
            System.out.println("NU pun DTO din cache pt. judetId: " + judetId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<JudetDto> judetDto = judetService.findDtoById(judetId);
        judetDto.ifPresent(unitateDto::setJudetDto);
    }

    private void setLocalitateDtoFromCache(UnitateDto unitateDto) {
        Integer localitateId = unitateDto.getLocalitateId();
        if (localitateId == null || unitateDto.getLocalitateDto() != null) {
            System.out.println("NU pun DTO din cache pt. localitateId: " + localitateId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<LocalitateDto> localitateDto = localitateService.findDtoById(localitateId);
        localitateDto.ifPresent(unitateDto::setLocalitateDto);
    }

    private void setTipStradaDtoFromCache(UnitateDto unitateDto) {
        Integer tipStradaId = unitateDto.getTipStradaId();
        if (tipStradaId == null || unitateDto.getTipStradaDto() != null) {
            System.out.println("NU pun DTO din cache pt. tipStradaId: " + tipStradaId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<TipStradaDto> tipStradaDto = tipStradaService.findDtoById(tipStradaId);
        tipStradaDto.ifPresent(unitateDto::setTipStradaDto);
    }

    private void setPartenerDtoFromCache(UnitateDto unitateDto) {
        Integer partenerID = unitateDto.getPartenerID();
        if (partenerID == null || unitateDto.getPartenerDto() != null) {
            System.out.println("NU pun DTO din cache pt. partenerID: " + partenerID);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<PartenerDto> partenerDto = partenerService.findDtoById(partenerID);
        partenerDto.ifPresent(unitateDto::setPartenerDto);
    }

    private void  setDtoFromCache(UnitateDto unitateDto){
        setJudetDtoFromCache(unitateDto);
        setLocalitateDtoFromCache(unitateDto);
        setTaraDtoFromCache(unitateDto);
        setPartenerDtoFromCache(unitateDto);
        setTipStradaDtoFromCache(unitateDto);
    }
}
