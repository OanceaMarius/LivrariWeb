package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.JudetService;
import ro.papetti.pluriva.dto.JudetDTOI;
import ro.papetti.pluriva.entity.Judet;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class JudetRestController {

    private final JudetService judetService;

    public JudetRestController(JudetService judetService) {
        this.judetService = judetService;
    }

    @GetMapping("/Judet")
    public List<Judet> findJudetAll(){
        return judetService.findAll();
    }

    @GetMapping("/JudetDTO")
    public List<JudetDTOI> findJudetDTOAll(){
        return judetService.findDTOAll(JudetDTOI.class);
    }

    @GetMapping("/JudetDTO/{judetId}")
    public ResponseEntity<JudetDTOI> findJudetDTOById(@NonNull @PathVariable int judetId){
        JudetDTOI entity = judetService.findDTOById(judetId,JudetDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc JudetDTO cu judetId: " + judetId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Judet/{judetID}")
    public ResponseEntity<Judet> findJudetById(@NonNull @PathVariable int judetID){
        Judet entity = judetService.findById(judetID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Um cu umId: " + judetID));
        return ResponseEntity.ok(entity);
    }
}
