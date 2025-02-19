package ro.papetti.livrari.plu.controlers.rest;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.TipLivrareService;
import ro.papetti.pluriva.dtoi.TipLivrareDTOI;
import ro.papetti.pluriva.entity.TipLivrare;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class TipLivrareRestController {
    private final TipLivrareService tipLivrareService;


    public TipLivrareRestController(TipLivrareService tipLivrareService) {
        this.tipLivrareService = tipLivrareService;
    }

    @GetMapping("/TipLivrare")
    public List<TipLivrare> findTipLivrareAll(){
        return tipLivrareService.findAll();
    }

    @GetMapping("/TipLivrareDTO")
    public List<TipLivrareDTOI> findTipLivrareDTOAll(){
        return tipLivrareService.findDTOAll(TipLivrareDTOI.class);
    }

    @GetMapping("/TipLivrareDTO/{tipLivrareId}")
    public ResponseEntity<TipLivrareDTOI> findTipLivrareDTOById(@NonNull @PathVariable int tipLivrareId){
        TipLivrareDTOI entity = tipLivrareService.findDTOById(tipLivrareId,TipLivrareDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipLivrareDTO cu tipLivrareId: " + tipLivrareId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TipLivrare/{tipLivrareId}")
    public ResponseEntity<TipLivrare> findTipLivrareById(@NonNull @PathVariable int tipLivrareId){
        TipLivrare entity = tipLivrareService.findById(tipLivrareId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipLivrare cu tipLivrareId: " + tipLivrareId));
        return ResponseEntity.ok(entity);
    }

}
