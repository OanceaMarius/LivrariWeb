package ro.papetti.livrari.plu.services;

import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.LeadDto;
import ro.papetti.pluriva.entity.Lead;

import java.util.List;
import java.util.Optional;


public interface LeadService extends BaseService<Lead, Integer> {

    Optional<LeadDto> findDtoById(int leadId);

    List<LeadDto> findDtoAll();

}
