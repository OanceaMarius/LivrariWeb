package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.TipDocService;
import ro.papetti.pluriva.dto.TipDocDTOI;
import ro.papetti.pluriva.entity.TipDoc;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class TipDocRestController {

    private final TipDocService tipDocService;


    public TipDocRestController(TipDocService tipDocService) {
        this.tipDocService = tipDocService;
    }


    @GetMapping("/TipDoc")
    public List<TipDoc> findTipDocAll(){
        return tipDocService.findAll();
    }

    @GetMapping({"/TipDocDTO","/TipDocDTO/"})
    public List<TipDocDTOI> findTipDocDTOAll(){
        return tipDocService.findDTOAll(TipDocDTOI.class);
    }

    @GetMapping("/TipDocDTO/{tipDocId}")
    public ResponseEntity<TipDocDTOI> findTipDocDTOById(@NonNull @PathVariable int tipDocId){
        TipDocDTOI entity = tipDocService.findDTOById(tipDocId,TipDocDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipDocDTO cu tipDocId: " + tipDocId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TipDoc/{tipDocId}")
    public ResponseEntity<TipDoc> findTipDocById(@NonNull @PathVariable int tipDocId){
        TipDoc entity = tipDocService.findById(tipDocId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipDoc cu tipDocId: " + tipDocId));
        return ResponseEntity.ok(entity);
    }


}
