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
import ro.papetti.livrari.plu.services.TvaService;
import ro.papetti.pluriva.dto.TvaDto;
import ro.papetti.pluriva.dtoi.TvaDTOI;
import ro.papetti.pluriva.entity.Tva;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class TvaRestComtroller {

    private final TvaService tvaService;


    @GetMapping("/Tva")
    public List<Tva> findTvaAll(){
        return tvaService.findAll();
    }

    @GetMapping("/TvaDTO")
    public List<TvaDto> findTvaDtoAll(){
        return tvaService.findDtoAll();
    }

    @GetMapping("/TvaDTO/{tvaId}")
    public ResponseEntity<TvaDto> findTvaDtoById(@NonNull @PathVariable int tvaId){
        TvaDto entity = tvaService.findDtoById(tvaId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TvaDto cu tvaId: "+tvaId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Tva/{tvaId}")
    public ResponseEntity<Tva> findTvaById(@NonNull @PathVariable int tvaId){
        Tva entity = tvaService.findById(tvaId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Tva cu tvaId: "+tvaId));
        return ResponseEntity.ok(entity);
    }
}
