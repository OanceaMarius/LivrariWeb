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
import ro.papetti.livrari.plu.services.TipLivrareService;
import ro.papetti.pluriva.dto.TipLivrareDto;
import ro.papetti.pluriva.dtoi.TipLivrareDTOI;
import ro.papetti.pluriva.entity.TipLivrare;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class TipLivrareRestController {
    private final TipLivrareService tipLivrareService;

    @GetMapping("/TipLivrare")
    public List<TipLivrare> findTipLivrareAll(){
        return tipLivrareService.findAll();
    }

    @GetMapping("/TipLivrareDTO")
    public List<TipLivrareDto> findTipLivrareDtoAll(){
        return tipLivrareService.findDtoAll();
    }

    @GetMapping("/TipLivrareDTO/{tipLivrareId}")
    public ResponseEntity<TipLivrareDto> findTipLivrareDtoById(@NonNull @PathVariable int tipLivrareId){
        TipLivrareDto entity = tipLivrareService.findDtoById(tipLivrareId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipLivrareDto cu tipLivrareId: " + tipLivrareId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TipLivrare/{tipLivrareId}")
    public ResponseEntity<TipLivrare> findTipLivrareById(@NonNull @PathVariable int tipLivrareId){
        TipLivrare entity = tipLivrareService.findById(tipLivrareId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipLivrare cu tipLivrareId: " + tipLivrareId));
        return ResponseEntity.ok(entity);
    }

}
