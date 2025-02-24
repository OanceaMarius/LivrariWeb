package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.papetti.livrari.plu.services.ProdusService;
import ro.papetti.pluriva.dto.ProdusDto;
import ro.papetti.pluriva.dtoi.ProdusDTOI;
import ro.papetti.pluriva.entity.Produs;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class ProdusRestController {
    private final ProdusService produsService;


    @GetMapping("/Produs")
    public List<Produs> findProdusAll(){
        return produsService.findAll();
    }


    @GetMapping("/ProdusDTO")
    public List<ProdusDto> findProdusDtoAll(){
        return produsService.findDtoAll();
    }

    @GetMapping("/ProdusDTO/{produsId}")
    public ResponseEntity<ProdusDto> findProdusDTOById(@NonNull @PathVariable int produsId){
        ProdusDto entity = produsService.findDtoById(produsId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc ProdusDto cu produsId: " + produsId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Produs/{produsId}")
    public ResponseEntity<Produs> findProdusById(@NonNull @PathVariable int produsId){
        Produs entity = produsService.findEagerById(produsId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Produs cu produsId: " + produsId));
        return ResponseEntity.ok(entity);
    }



}
