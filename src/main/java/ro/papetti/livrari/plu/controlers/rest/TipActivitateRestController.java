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
import ro.papetti.livrari.plu.services.TipActivitateService;
import ro.papetti.pluriva.dto.BrandDto;
import ro.papetti.pluriva.dto.TipActivitateDto;
import ro.papetti.pluriva.entity.Brand;
import ro.papetti.pluriva.entity.TipActivitate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api/pluriva")
public class TipActivitateRestController {

    private final TipActivitateService tipActivitateService;

    @GetMapping("/TipActivitate")
    public List<TipActivitate> findTipActivitateAll(){
        return tipActivitateService.findAll();
    }

    @GetMapping("/TipActivitateDTO")
    public List<TipActivitateDto> findTipActivitateDtoAll(){
        return tipActivitateService.findDtoAll();
    }

    @GetMapping("/TipActivitateDTO/{tipActivitateID}")
    public ResponseEntity<TipActivitateDto> findTipActivitateDtoById(@NonNull @PathVariable int tipActivitateID){
        TipActivitateDto entity = tipActivitateService.findDtoById(tipActivitateID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipActivitateDto cu tipActivitateID: "+tipActivitateID));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TipActivitate/{tipActivitateID}")
    public ResponseEntity<TipActivitate> findtipActivitateIDById(@NonNull @PathVariable int tipActivitateID){
        TipActivitate entity = tipActivitateService.findById(tipActivitateID)
                .orElseThrow(()-> new EntityNotFoundException("Nu gasesc TipActivitate cu tipActivitateID: "+tipActivitateID));
        return ResponseEntity.ok(entity);
    }
}
