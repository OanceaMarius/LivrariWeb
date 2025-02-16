package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.LocalitateService;
import ro.papetti.pluriva.dto.LocalitateDTOI;
import ro.papetti.pluriva.entity.Localitate;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class LocalitateRestController {
    private final LocalitateService localitateService;


    public LocalitateRestController(LocalitateService localitateService) {
        this.localitateService = localitateService;
    }

    @GetMapping("/Localitate")
    public List<Localitate> findLocalitateAll(){
        return localitateService.findAll();
    }

    @GetMapping("/LocalitateDTO")
    public List<LocalitateDTOI> findLocalitateDTOAll(){
        return localitateService.findDTOAll(LocalitateDTOI.class);
    }

    @GetMapping("/LocalitateDTO/{localitateID}")
    public ResponseEntity<LocalitateDTOI> findUmDTOById(@NonNull @PathVariable int localitateID){
        LocalitateDTOI entity = localitateService.findDTOById(localitateID,LocalitateDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc LocalitateDTO cu localitateID: " + localitateID));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Localitate/{localitateID}")
    public ResponseEntity<Localitate> findUmById(@NonNull @PathVariable int localitateID){
        Localitate entity = localitateService.findById(localitateID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Localitate cu localitateID: " + localitateID));
        return ResponseEntity.ok(entity);
    }

}
