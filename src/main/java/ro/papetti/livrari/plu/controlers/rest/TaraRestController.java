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
import ro.papetti.livrari.plu.services.TaraService;
import ro.papetti.pluriva.dto.TaraDto;
import ro.papetti.pluriva.dtoi.TaraDTOI;
import ro.papetti.pluriva.entity.Tara;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class TaraRestController {

    private final TaraService taraService;


    @GetMapping("/Tara")
    public List<Tara> findTaraAll(){
        return taraService.findAll();
    }

    @GetMapping("/TaraDTO")
    public List<TaraDto> findTaraDtoAll(){
        return taraService.findDtoAll();
    }

    @GetMapping("/TaraDTO/{taraID}")
    public ResponseEntity<TaraDto> findTaraDtoById(@NonNull @PathVariable int taraID){
        TaraDto entity = taraService.findDtoById(taraID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TaraDto cu umId: " + taraID));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Tara/{taraID}")
    public ResponseEntity<Tara> findTaraById(@NonNull @PathVariable int taraID){
        Tara entity = taraService.findById(taraID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Tara cu taraID: " + taraID));
        return ResponseEntity.ok(entity);
    }


}
