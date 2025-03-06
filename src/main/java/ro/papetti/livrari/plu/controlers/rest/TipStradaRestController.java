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
import ro.papetti.livrari.plu.services.TipStradaService;
import ro.papetti.pluriva.dto.TipStradaDto;
import ro.papetti.pluriva.entity.TipStrada;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class TipStradaRestController {
    private final TipStradaService tipStradaService;


    @GetMapping("/TipStrada")
    public List<TipStrada> findTipStradaAll(){
        return tipStradaService.findAll();
    }

    @GetMapping("/TipStrada/{tipStradaId}")
    public ResponseEntity<TipStrada> findTipStradaById(@NonNull @PathVariable int tipStradaId){
        TipStrada entity = tipStradaService.findById(tipStradaId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipStrada cu tipStradaId: " + tipStradaId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TipStradaDTO")
    public List<TipStradaDto> findTipStradaDtoAll(){
        return tipStradaService.findDtoAll();
    }

    @GetMapping("/TipStradaDTO/{tipStradaId}")
    public ResponseEntity<TipStradaDto> findTipStradaDtoById(@NonNull @PathVariable int tipStradaId){
        TipStradaDto entity = tipStradaService.findDtoById(tipStradaId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipStradaDto cu tipStradaId: " + tipStradaId));
        return ResponseEntity.ok(entity);
    }


}
