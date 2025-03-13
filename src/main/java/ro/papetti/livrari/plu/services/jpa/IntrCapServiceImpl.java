package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.IntrCapRepozitory;
import ro.papetti.livrari.plu.services.CompletareDtoService;
import ro.papetti.livrari.plu.services.IntrCapService;
import ro.papetti.livrari.plu.services.IntrPozService;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.dto.IntrCapDto;
import ro.papetti.pluriva.entity.IntrCap;
import ro.papetti.pluriva.mapstruct.IntrCapMapStruct;

import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class IntrCapServiceImpl extends BaseServiceImpl<IntrCap, IntrCapRepozitory> implements IntrCapService {
    public IntrCapServiceImpl(IntrCapRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private CompletareDtoService completareDtoService;
    @Autowired
    private IntrCapMapStruct intrCapMapStruct;
    @Autowired
    private UnitateService unitateService;
    @Autowired
    private IntrPozService intrPozService;


    @Override
    public Optional<IntrCapDto> findDtoById(int intrCapId) {
        Optional<IntrCap> intrCap = findById(intrCapId);
        Optional<IntrCapDto> optionalIntrCapDto = intrCap.map(value -> intrCapMapStruct.toDto(value));
        if (optionalIntrCapDto.isPresent()) {
            optionalIntrCapDto.get().setFurnizorUnitateDto(unitateService.findDtoById(optionalIntrCapDto.get().getFurnizorId()).orElse(null));
            optionalIntrCapDto.get().setTipDocDto(completareDtoService.getTipDocDtoById(optionalIntrCapDto.get().getTipDocId()));
            optionalIntrCapDto.get().setDocDto(completareDtoService.getDocDtoById(optionalIntrCapDto.get().getDocId()));
            optionalIntrCapDto.get().setGestiuneDto(completareDtoService.getGestiuneDtoById(optionalIntrCapDto.get().getGestiuneId()));
            optionalIntrCapDto.get().setUserCreareDto(completareDtoService.getUserDtoById(optionalIntrCapDto.get().getUserCreareId()));
            optionalIntrCapDto.get().setUserValidareDto(completareDtoService.getUserDtoById(optionalIntrCapDto.get().getUserValidareId()));
            optionalIntrCapDto.get().setUserAnulareDto(completareDtoService.getUserDtoById(optionalIntrCapDto.get().getUserAnulareId()));
            optionalIntrCapDto.get().setPozitiiDto(intrPozService.findDtoByIntrCapId(optionalIntrCapDto.get().getIntrCapId()));
        }

        return optionalIntrCapDto;
    }

    @Override
    public Optional<IntrCap> findEagerById(int intrCapId){
        return rep.findEagerById(intrCapId);
    }

}
