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
    @Autowired
    private CompletareDtoService completareDtoService;


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

    private void setDtoFromCache(PartenerDto partenerDto) {

        partenerDto.setTaraDto(completareDtoService.getTaraDtoById(partenerDto.getTaraId()));
        partenerDto.setJudetDto(completareDtoService.getJudetDtoById(partenerDto.getJudetId()));
        partenerDto.setLocalitateDto(completareDtoService.getLocalitateDtoById(partenerDto.getLocalitateId()));
        partenerDto.setTipFirmaDto(completareDtoService.getTipFirmaById(partenerDto.getTipFirmaId()));
        partenerDto.setTipStradaDto(completareDtoService.getTipStradaDtoById(partenerDto.getTipStradaId()));
        partenerDto.setUserIntroducereDto(completareDtoService.getUserDtoById(partenerDto.getUserIntroducereId()));
        partenerDto.setUserModificareDto(completareDtoService.getUserDtoById(partenerDto.getUserModificareId()));

    }




}
