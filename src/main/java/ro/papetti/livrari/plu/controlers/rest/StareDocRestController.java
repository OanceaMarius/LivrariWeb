package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.StareDocService;
import ro.papetti.pluriva.dto.StareDocDTOI;
import ro.papetti.pluriva.entity.StareDoc;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class StareDocRestController {
    private final StareDocService stareDocService;

    public StareDocRestController(StareDocService stareDocService) {
        this.stareDocService = stareDocService;
    }

    @GetMapping("/StareDoc")
    public List<StareDoc> findStareDocAll(){
        return stareDocService.findAll();
    }

    @GetMapping("/StareDocDTO")
    public List<StareDocDTOI> findStareDocDTOAll(){
        return stareDocService.findDTOAll(StareDocDTOI.class);
    }

    @GetMapping("/StareDocDTO/{stareId}")
    public ResponseEntity<StareDocDTOI> findStareDocDTOById(@NonNull @PathVariable int stareId){
        StareDocDTOI entity = stareDocService.findDTOById(stareId,StareDocDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc StareDocDTOI cu stareId: " + stareId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/StareDoc/{stareId}")
    public ResponseEntity<StareDoc> findStareDocById(@NonNull @PathVariable int stareId){
        StareDoc entity = stareDocService.findById(stareId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc StareDoc cu stareId: " + stareId));
        return ResponseEntity.ok(entity);
    }

}
