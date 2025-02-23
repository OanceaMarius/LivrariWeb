package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.PartenerRepozitory;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;
import ro.papetti.pluriva.entity.Partener;
import ro.papetti.pluriva.mapstruct.PartenerMapStruct;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class PartenerServiceImpl extends BaseServiceImpl<Partener, PartenerRepozitory> implements PartenerService {

    public PartenerServiceImpl(PartenerRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private PartenerMapStruct partenerMapStruct;
    @Autowired
    private TipStradaService tipStradaService;
    @Autowired
    private TipFirmaService tipFirmaService;
    @Autowired
    private TaraService taraService;
    @Autowired
    private JudetService judetService;
    @Autowired
    private LocalitateService localitateService;
    @Autowired
    private UserService userService;


    @Override
    public Optional<Partener> findEagerByPartenerID(int partenerID){
        return rep.findEagerByPartenerID(partenerID);
    }

    @Override
    public Optional<Partener> findById(int partenerID){
        return rep.findById(partenerID);
    }


    @Override
    @Cacheable(cacheNames = CacheName.PARTENER_DTO, key = "#partenerId")
    public Optional<PartenerDto> findDtoById(@NonNull int partenerId) {
        Optional<Partener> partener = findById(partenerId);
        Optional<PartenerDto> optionalPartenerDto = partener.map(value -> partenerMapStruct.toDto(value));
        if (optionalPartenerDto.isEmpty())
            return Optional.empty();

        setDtoFromCache(optionalPartenerDto.get());
        return optionalPartenerDto;
    }

    @Override
    public List<PartenerDto> findDtoAll() {
        List<PartenerDto> partenerDtoList = partenerMapStruct.toDtoList(findAll());
        for (PartenerDto partenerDto : partenerDtoList) {
            setDtoFromCache(partenerDto);
        }
        return partenerDtoList;
    }

    private void setDtoFromCache(PartenerDto optionalPartenerDto) {
        setJudetDtoFromCache(optionalPartenerDto);
        setLocalitateDtoFromCache(optionalPartenerDto);
        setTaraDtoFromCache(optionalPartenerDto);
        setTipFirmaDtoFromCache(optionalPartenerDto);
        setTipStradaDtoFromCache(optionalPartenerDto);
        setUserIntroducereDtoFromCache(optionalPartenerDto);
        setUserModificareDtoFromCache(optionalPartenerDto);
    }

    private void setTipFirmaDtoFromCache(PartenerDto partenerDto) {
        Integer tipFirmaId = partenerDto.getTipFirmaId();
        if (tipFirmaId == null || partenerDto.getTipFirmaDto() != null) {
            System.out.println("NU pun DTO din cache pt. tipFirmaId: " + tipFirmaId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<TipFirmaDto> tipFirmaDto = tipFirmaService.findDtoById(tipFirmaId);
        tipFirmaDto.ifPresent(partenerDto::setTipFirmaDto);

    }

    private void setTipStradaDtoFromCache(PartenerDto partenerDto) {
        Integer tipStradaId = partenerDto.getTipStradaId();
        if (tipStradaId == null || partenerDto.getTipStradaDto() != null) {
            System.out.println("NU pun DTO din cache pt. tipStradaId: " + tipStradaId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<TipStradaDto> tipStadaDto = tipStradaService.findDtoById(tipStradaId);
        tipStadaDto.ifPresent(partenerDto::setTipStradaDto);

    }

    private void setTaraDtoFromCache(PartenerDto partenerDto) {
        Integer taraId = partenerDto.getTaraId();
        if (taraId == null || partenerDto.getTaraDto() != null) {
            System.out.println("NU pun DTO din cache pt. taraId: " + taraId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<TaraDto> taraDto = taraService.findDtoById(taraId);
        taraDto.ifPresent(partenerDto::setTaraDto);

    }

    private void setJudetDtoFromCache(PartenerDto partenerDto) {
        Integer judetId = partenerDto.getJudetId();
        if (judetId == null || partenerDto.getJudetDto() != null) {
            System.out.println("NU pun DTO din cache pt. judetId: " + judetId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<JudetDto> judetDto = judetService.findDtoById(judetId);
        judetDto.ifPresent(partenerDto::setJudetDto);

    }

    private void setLocalitateDtoFromCache(PartenerDto partenerDto) {
        Integer localitateId = partenerDto.getJudetId();
        if (localitateId == null || partenerDto.getLocalitateDto() != null) {
            System.out.println("NU pun DTO din cache pt. judetId: " + localitateId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<LocalitateDto> localitateDto = localitateService.findDtoById(localitateId);
        localitateDto.ifPresent(partenerDto::setLocalitateDto);

    }

    private void setUserIntroducereDtoFromCache(PartenerDto partenerDto) {
        Integer userIntroducereId = partenerDto.getUserIntroducereId();
        if (userIntroducereId == null || partenerDto.getUserIntroducereDto() != null) {
            System.out.println("NU pun DTO din cache pt. userIntroducereId: " + userIntroducereId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<UserDto> userDto = userService.findDtoById(userIntroducereId);
        userDto.ifPresent(partenerDto::setUserIntroducereDto);

    }

    private void setUserModificareDtoFromCache(PartenerDto partenerDto) {
        Integer userModificareId = partenerDto.getUserModificareId();
        if (userModificareId == null || partenerDto.getUserModificareDto() != null) {
            System.out.println("NU pun DTO din cache pt. userModificareId: " + userModificareId);
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<UserDto> userDto = userService.findDtoById(userModificareId);
        userDto.ifPresent(partenerDto::setUserModificareDto);

    }


}
