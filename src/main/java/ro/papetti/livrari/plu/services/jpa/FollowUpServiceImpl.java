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
import ro.papetti.livrari.plu.services.FollowUpService;
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
    FollowUpMapStruct followUpMapStruct;

    @Override
    public Optional<FollowUpDto> findDtoById(int followupId) {
        Optional<FollowUp> followUp = rep.findById(followupId);
        return followUp.map(value-> followUpMapStruct.toDto(value));
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
}
