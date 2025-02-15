package ro.papetti.livrari.controllers.rest.plu;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.TermenPlataService;
import ro.papetti.pluriva.dto.TermenPlataDTOI;
import ro.papetti.pluriva.dto.UmDTOI;
import ro.papetti.pluriva.entity.TermenPlata;
import ro.papetti.pluriva.entity.Um;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class TermenPlataRestCantroller {

    private final TermenPlataService termenPlataService;

    public TermenPlataRestCantroller(TermenPlataService termenPlataService) {
        this.termenPlataService = termenPlataService;
    }


    @GetMapping("/TermenPlata")
    public List<TermenPlata> findTermenPlataAll(){
        return termenPlataService.findAll();
    }

    @GetMapping("/TermenPlataDTO")
    public List<TermenPlataDTOI> findTermenPlataDTOAll(){
        return termenPlataService.findDTOAll(TermenPlataDTOI.class);
    }

    @GetMapping("/TermenPlataDTO/{termenPlataID}")
    public ResponseEntity<TermenPlataDTOI> findTermenPlataDTOById(@NonNull @PathVariable int termenPlataID){
        TermenPlataDTOI entity = termenPlataService.findDTOById(termenPlataID,TermenPlataDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TermenPlata cu termenPlataID: " + termenPlataID));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TermenPlata/{termenPlataID}")
    public ResponseEntity<TermenPlata> findTermenPlataById(@NonNull @PathVariable int termenPlataID){
        TermenPlata entity = termenPlataService.findById(termenPlataID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TermenPlata cu termenPlataID: " + termenPlataID));
        return ResponseEntity.ok(entity);
    }
}
