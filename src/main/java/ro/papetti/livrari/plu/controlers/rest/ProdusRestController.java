package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.ProdusService;
import ro.papetti.pluriva.dtoi.ProdusDTOI;
import ro.papetti.pluriva.entity.Produs;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class ProdusRestController {
    private final ProdusService produsService;

    public ProdusRestController(ProdusService produsService) {
        this.produsService = produsService;
    }



    @GetMapping("/Produs")
    public List<Produs> findProdusAll(){
        return produsService.findAll();
    }

    @GetMapping("/ProdusDTO")
    public List<ProdusDTOI> findProdusDTOAll(){
        return produsService.findDTOAll(ProdusDTOI.class);
    }

    @GetMapping("/ProdusDTO/{produsId}")
    public ResponseEntity<ProdusDTOI> findProdusDTOById(@NonNull @PathVariable int produsId){
        ProdusDTOI entity = produsService.findDTOById(produsId,ProdusDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc ProdusDTOI cu produsId: " + produsId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Produs/{produsId}")
    public ResponseEntity<Produs> findProdusById(@NonNull @PathVariable int produsId){
        Produs entity = produsService.findById(produsId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Produs cu produsId: " + produsId));
        return ResponseEntity.ok(entity);
    }


}
