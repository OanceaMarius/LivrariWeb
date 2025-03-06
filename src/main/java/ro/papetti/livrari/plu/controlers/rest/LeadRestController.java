package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.LeadService;
import ro.papetti.pluriva.dto.LeadDto;
import ro.papetti.pluriva.entity.Lead;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class LeadRestController {
    private final  LeadService leadService;

    @GetMapping("/Lead")
    public List<Lead> findLeadAll() {
        return leadService.findAll();
    }


    @GetMapping("/Lead/{leadId}")
    public ResponseEntity<Lead> findLeadById(@NonNull @PathVariable int leadId) {
        Lead entity = leadService.findEagerById(leadId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Lead cu leadId: "+ leadId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/LeadDTO")
    public List<LeadDto> findLeadDtoAll() {
        return leadService.findDtoAll();
    }


    @GetMapping("/LeadDTO/{leadId}")
    public ResponseEntity<LeadDto> findLeadDtoById(@NonNull @PathVariable int leadId) {
        LeadDto entity = leadService.findDtoById(leadId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Lead cu leadId: "+ leadId));
        return ResponseEntity.ok(entity);
    }

}
