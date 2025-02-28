/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.FollowUpRepozitory;
import ro.papetti.livrari.plu.services.CompletareDtoService;
import ro.papetti.livrari.plu.services.FollowUpService;
import ro.papetti.livrari.plu.services.LeadService;
import ro.papetti.pluriva.dto.FollowUpDto;
import ro.papetti.pluriva.entity.FollowUp;
import ro.papetti.pluriva.mapstruct.FollowUpMapStruct;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class FollowUpServiceImpl extends BaseServiceImpl<FollowUp, FollowUpRepozitory>
        implements FollowUpService{

    public FollowUpServiceImpl(FollowUpRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private FollowUpMapStruct followUpMapStruct;
    @Autowired
    private CompletareDtoService completareDtoService;
    @Autowired
    private LeadService leadService;

    @Override
    public Optional<FollowUp> findEagerById(int followUpId){
        return rep.findEagerById(followUpId);
    }


    @Override
    public Optional<FollowUpDto> findDtoById(int followupId) {
        Optional<FollowUp> followUp = rep.findById(followupId);
        if (followUp.isEmpty())
            return Optional.empty();
        FollowUpDto followUpDto = followUpMapStruct.toDto(followUp.get());
        setFollowUpDtoByCache(followUpDto);
        return Optional.of(followUpDto);
    }

    @Override
    public List<FollowUpDto> findDtoAll() {
        List<FollowUp> followUpList = rep.findAll();
        return followUpMapStruct.toDtoList(followUpList);
    }

    @Override
    public List<FollowUpDto> findDtoDataCreareDupa(Date data) {
        return followUpMapStruct.toDtoList(rep.findByDataCreareDupa(data));
    }

    @Override
    public List<FollowUpDto> findDtoByTipActivitate(int tipActivitate) {
        return followUpMapStruct.toDtoList(rep.findByTipActivitate(tipActivitate));
    }

    @Override
    public List<FollowUpDto> findDtoByTipActivitateSiDataCreareDupa(int tipActivitate, Date dataCreare) {
        return followUpMapStruct.toDtoList(rep.findByTipActivitateSiDataCreareDupa(tipActivitate, dataCreare));
    }

    private void setFollowUpDtoByCache(FollowUpDto followUpDto){
        followUpDto.setLeadDto(leadService.findDtoById(followUpDto.getLeadId()).orElse(null));
        followUpDto.setUserCreareDto(completareDtoService.getUserDtoById(followUpDto.getUserCreareId()));
        followUpDto.setTipActivitateDto(completareDtoService.getTipActivitateDtoById(followUpDto.getTipActivitateFollowUp()));
    }
}
