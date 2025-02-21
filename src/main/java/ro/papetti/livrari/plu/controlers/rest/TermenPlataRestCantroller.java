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
import ro.papetti.livrari.plu.services.TermenPlataService;
import ro.papetti.pluriva.dto.TermenPlataDto;
import ro.papetti.pluriva.dtoi.TermenPlataDTOI;
import ro.papetti.pluriva.entity.TermenPlata;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class TermenPlataRestCantroller {

    private final TermenPlataService termenPlataService;



    @GetMapping("/TermenPlata")
    public List<TermenPlata> findTermenPlataAll(){
        return termenPlataService.findAll();
    }

    @GetMapping("/TermenPlataDTO")
    public List<TermenPlataDto> findTermenPlataDtoAll(){
        return termenPlataService.findDtoAll();
    }

    @GetMapping("/TermenPlataDTO/{termenPlataID}")
    public ResponseEntity<TermenPlataDto> findTermenPlataDtoById(@NonNull @PathVariable int termenPlataID){
        TermenPlataDto entity = termenPlataService.findDtoById(termenPlataID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TermenPlataDto cu termenPlataID: " + termenPlataID));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TermenPlata/{termenPlataID}")
    public ResponseEntity<TermenPlata> findTermenPlataById(@NonNull @PathVariable int termenPlataID){
        TermenPlata entity = termenPlataService.findById(termenPlataID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TermenPlata cu termenPlataID: " + termenPlataID));
        return ResponseEntity.ok(entity);
    }
}
