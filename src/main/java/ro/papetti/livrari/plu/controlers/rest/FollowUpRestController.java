package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.FollowUpService;
import ro.papetti.pluriva.dto.FollowUpDto;
import ro.papetti.pluriva.entity.Cpv;
import ro.papetti.pluriva.entity.FollowUp;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class FollowUpRestController {

    private final FollowUpService followUpService;

    @GetMapping("/FollowUp")
    public List<FollowUp> findFollowUpAll() {
        return followUpService.findAll();
    }


    @GetMapping("/FollowUp/{followupId}")
    public ResponseEntity<FollowUp> findFollowUpById(@NonNull @PathVariable int followupId) {
        FollowUp entity = followUpService.findById(followupId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc FollowUp cu followupId: "+ followupId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/FollowUpDTO")
    public List<FollowUpDto> findFollowUpDtoAll() {
        return followUpService.findDtoAll();
    }


    @GetMapping("/FollowUpDTO/{followupId}")
    public ResponseEntity<FollowUpDto> findFollowUpDtoById(@NonNull @PathVariable int followupId) {
        FollowUpDto entity = followUpService.findDtoById(followupId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc FollowUpDto cu followupId: "+ followupId));
        return ResponseEntity.ok(entity);
    }

}
