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
import ro.papetti.livrari.plu.services.UmService;
import ro.papetti.pluriva.dto.UmDto;
import ro.papetti.pluriva.dtoi.UmDTOI;
import ro.papetti.pluriva.entity.Um;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class UmRestComtroller {

    private final UmService umService;

    @GetMapping("/Um")
    public List<Um> findUmAll(){
        return umService.findAll();
    }

    @GetMapping("/UmDTO")
    public List<UmDto> findUmDtoAll(){
        return umService.findDtoAll();
    }

    @GetMapping("/UmDTO/{umId}")
    public ResponseEntity<UmDto> findUmDtoyId(@NonNull @PathVariable int umId){
        UmDto entity = umService.findDtoById(umId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc UmDto cu umId: " + umId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Um/{umId}")
    public ResponseEntity<Um> findUmById(@NonNull @PathVariable int umId){
        Um entity = umService.findById(umId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Um cu umId: " + umId));
        return ResponseEntity.ok(entity);
    }


}
