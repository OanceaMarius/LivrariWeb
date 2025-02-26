package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.LeadRepository;
import ro.papetti.livrari.plu.services.CompletareDtoService;
import ro.papetti.livrari.plu.services.LeadService;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.livrari.plu.services.UserService;
import ro.papetti.pluriva.dto.LeadDto;
import ro.papetti.pluriva.entity.Lead;
import ro.papetti.pluriva.mapstruct.LeadMapStruct;

import java.util.List;
import java.util.Optional;

@Service
public class LeadServiceImpl extends BaseServiceImpl<Lead, LeadRepository> implements LeadService {
    @Autowired
    private UserService userService;
    @Autowired
    private UnitateService unitateService;
    @Autowired
    private CompletareDtoService completareDtoService;

    public LeadServiceImpl(LeadRepository repozitory) {
        super(repozitory);
    }
    @Autowired
    private LeadMapStruct leadMapStruct;

    @Override
    public Optional<LeadDto> findDtoById(int leadId) {
        Optional<Lead>lead=rep.findById(leadId);
        if (lead.isEmpty()){
            return Optional.empty();
        }
        LeadDto leadDto = leadMapStruct.toDto(lead.get());
        setLeadDtoByCache(leadDto);
        return Optional.of(leadDto);
    }

    @Override
    public List<LeadDto> findDtoAll() {
        return leadMapStruct.toDtoList(rep.findAll());
    }

    @Override
    public Optional<Lead> findEagerById(int leadId){
        return rep.findEagerById(leadId);
    }

    private void setLeadDtoByCache(LeadDto leadDto){
                leadDto.setUnitateClientDto(unitateService.findDtoById(leadDto.getClientId()).orElse(null));
                leadDto.setUserCreareDto(completareDtoService
                        .getUserDtoById(leadDto.getUserCreareID()));
                leadDto.setTaraDto(completareDtoService
                        .getTaraDtoById(leadDto.getTaraId()));
                leadDto.setJudetDto(completareDtoService
                        .getJudetDtoById(leadDto.getJudetId()));
                leadDto.setLocalitateDto(completareDtoService
                        .getLocalitateDtoById(leadDto.getLocalitateId()));

    }

}
