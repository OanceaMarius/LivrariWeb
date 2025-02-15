package ro.papetti.livrari.controllers.rest.plu;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.TvaService;
import ro.papetti.pluriva.dto.TvaDTOI;
import ro.papetti.pluriva.entity.Tva;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class TvaRestComtroller {

    private final TvaService tvaService;

    public TvaRestComtroller(TvaService tvaService) {
        this.tvaService = tvaService;
    }

    @GetMapping("/Tva")
    public List<Tva> findTvaAll(){
        return tvaService.findAll();
    }

    @GetMapping("/TvaDTO")
    public List<TvaDTOI> findTvaDTOAll(){
        return tvaService.findDTOAll(TvaDTOI.class);
    }

    @GetMapping("/TvaDTO/{tvaId}")
    public ResponseEntity<TvaDTOI> findTvaDTOById(@NonNull @PathVariable int tvaId){
        TvaDTOI entity = tvaService.findDTOById(tvaId,TvaDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TvaDTO cu tvaId: "+tvaId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Tva/{tvaId}")
    public ResponseEntity<Tva> findTvaById(@NonNull @PathVariable int tvaId){
        Tva entity = tvaService.findById(tvaId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Tva cu tvaId: "+tvaId));
        return ResponseEntity.ok(entity);
    }
}
