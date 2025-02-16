package ro.papetti.livrari.plu.controlers.rest;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.UmService;
import ro.papetti.pluriva.dto.UmDTOI;
import ro.papetti.pluriva.entity.Um;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class UmRestComtroller {

    private final UmService umService;

    public UmRestComtroller(UmService umService) {
        this.umService = umService;
    }




    @GetMapping("/Um")
    public List<Um> findUmAll(){
        return umService.findAll();
    }

    @GetMapping("/UmDTO")
    public List<UmDTOI> findUmDTOAll(){
        return umService.findDTOAll(UmDTOI.class);
    }

    @GetMapping("/UmDTO/{umId}")
    public ResponseEntity<UmDTOI> findUmDTOById(@NonNull @PathVariable int umId){
        UmDTOI entity = umService.findDTOById(umId,UmDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc UmDTO cu umId: " + umId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Um/{umId}")
    public ResponseEntity<Um> findUmById(@NonNull @PathVariable int umId){
        Um entity = umService.findById(umId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Um cu umId: " + umId));
        return ResponseEntity.ok(entity);
    }


}
