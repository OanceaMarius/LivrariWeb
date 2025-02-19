package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.LeadRepository;
import ro.papetti.livrari.plu.services.LeadService;
import ro.papetti.pluriva.dto.LeadDto;
import ro.papetti.pluriva.entity.Lead;
import ro.papetti.pluriva.mapstruct.LeadMapStruct;

import java.util.List;
import java.util.Optional;

@Service
public class LeadServiceImpl extends BaseServiceImpl<Lead, LeadRepository> implements LeadService {


    public LeadServiceImpl(LeadRepository repozitory) {
        super(repozitory);
    }
    @Autowired
    private LeadMapStruct leadMapStruct;

    @Override
    public Optional<LeadDto> findDtoById(int leadId) {
        Optional<Lead>lead=rep.findById(leadId);
        return lead.map(value->leadMapStruct.toDto(value));
    }

    @Override
    public List<LeadDto> findDtoAll() {
        return leadMapStruct.toDtoList(rep.findAll());
    }
}
