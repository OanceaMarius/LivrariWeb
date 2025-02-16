package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.TipFirmaService;
import ro.papetti.pluriva.entity.TipFirma;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class TipFirmaRestController {

    private final TipFirmaService tipFirmaService;


    public TipFirmaRestController(TipFirmaService tipFirmaService) {
        this.tipFirmaService = tipFirmaService;
    }

    @GetMapping("/TipFirma")
    public List<TipFirma> findTipFirmaAll(){
        return tipFirmaService.findAll();
    }


    @GetMapping("/TipFirma/{tipFirmaId}")
    public ResponseEntity<TipFirma> findTipFirmaById(@NonNull @PathVariable int tipFirmaId){
        TipFirma entity = tipFirmaService.findById(tipFirmaId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipFirma cu tipFirmaId: " + tipFirmaId));
        return ResponseEntity.ok(entity);
    }

}
