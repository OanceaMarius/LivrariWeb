package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.IesCapRepozirory;
import ro.papetti.livrari.plu.services.CompletareDtoService;
import ro.papetti.livrari.plu.services.IesCapService;
import ro.papetti.livrari.plu.services.IesPozService;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.dto.IesCapDto;
import ro.papetti.pluriva.entity.IesCap;
import ro.papetti.pluriva.mapstruct.IesCapMapStruct;

import java.util.Optional;
@Service
@Transactional("plurivaTransactionManager")
public class IesCapServiceImpl extends BaseServiceImpl<IesCap, IesCapRepozirory>implements IesCapService {
    public IesCapServiceImpl(IesCapRepozirory repozitory) {
        super(repozitory);
    }
    @Autowired
    private IesCapMapStruct iesCapMapStruct;
    @Autowired
    private UnitateService unitateService;
    @Autowired
    private CompletareDtoService completareDtoService;
    @Autowired
    private IesPozService iesPozService;

    @Override
    public Optional<IesCapDto> findDtoById(int iesCapId) {
        Optional<IesCap> optionalIesCap = rep.findById(iesCapId);
        Optional<IesCapDto>optionalIesCapDto= optionalIesCap.map(value->iesCapMapStruct.toDto(value));
        if (optionalIesCapDto.isPresent()){
            optionalIesCapDto.get().setClientUnitateDto(unitateService.findDtoById(optionalIesCapDto.get().getClientId()).orElse(null));
            optionalIesCapDto.get().setTipDocDto(completareDtoService.getTipDocDtoById(optionalIesCapDto.get().getTipDocId()));
            optionalIesCapDto.get().setUserCreareDto(completareDtoService.getUserDtoById(optionalIesCapDto.get().getUserCreareId()));
            optionalIesCapDto.get().setUserFacturareDto(completareDtoService.getUserDtoById(optionalIesCapDto.get().getUserFacturareId()));
            optionalIesCapDto.get().setUserValidareContaDto(completareDtoService.getUserDtoById(optionalIesCapDto.get().getUserValidareContaId()));
            optionalIesCapDto.get().setUserAnulareDto(completareDtoService.getUserDtoById(optionalIesCapDto.get().getUserAnulareId()));
            optionalIesCapDto.get().setUserModificareDto(completareDtoService.getUserDtoById(optionalIesCapDto.get().getUserModificareId()));
            optionalIesCapDto.get().setUserValidareDto(completareDtoService.getUserDtoById(optionalIesCapDto.get().getUserValidareId()));
            optionalIesCapDto.get().setTermenPlataDto(completareDtoService.getTermenPlataDtoById(optionalIesCapDto.get().getTermenPlataId()));
            optionalIesCapDto.get().setPozitiiDto(iesPozService.findDtoByIesCapId(optionalIesCapDto.get().getIesCapId()));

        }


        return optionalIesCapDto;
    }

    @Override
    public Optional<IesCap> findEagerById(int iesCapId) {
        return rep.findEagerById(iesCapId);
    }
}
