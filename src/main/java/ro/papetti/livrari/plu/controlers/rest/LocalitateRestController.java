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
import ro.papetti.livrari.plu.services.LocalitateService;
import ro.papetti.pluriva.dto.LocalitateDto;
import ro.papetti.pluriva.dtoi.LocalitateDTOI;
import ro.papetti.pluriva.entity.Localitate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class LocalitateRestController {
    private final LocalitateService localitateService;


    @GetMapping("/Localitate")
    public List<Localitate> findLocalitateAll(){
        return localitateService.findAll();
    }

    @GetMapping("/LocalitateDTO")
    public List<LocalitateDto> findLocalitateDtoAll(){
        return localitateService.findDtoAll();
    }

    @GetMapping("/LocalitateDTO/{localitateID}")
    public ResponseEntity<LocalitateDto> findUmDtoById(@NonNull @PathVariable int localitateID){
        LocalitateDto entity = localitateService.findDtoById(localitateID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc LocalitateDto cu localitateID: " + localitateID));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Localitate/{localitateID}")
    public ResponseEntity<Localitate> findUmById(@NonNull @PathVariable int localitateID){
        Localitate entity = localitateService.findById(localitateID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Localitate cu localitateID: " + localitateID));
        return ResponseEntity.ok(entity);
    }

}
