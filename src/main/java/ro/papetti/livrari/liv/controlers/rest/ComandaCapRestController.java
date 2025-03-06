package ro.papetti.livrari.liv.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.CoordonateFixe;
import ro.papetti.livrari.liv.repozitories.ComandaCapRepozitory;
import ro.papetti.livrari.liv.services.ComandaCapService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ComandaCapRestController {
    private final ComandaCapService comandaCapService;
    private final ComandaCapRepozitory comandaCapRepozitory;

    @GetMapping("/ComandaCap/{capId}")
    public ResponseEntity<ComandaCap> findComandaCapById(@NonNull @PathVariable int capId){
        ComandaCap entity = comandaCapService.findById(capId)
                .orElseThrow(()-> new EntityNotFoundException("Nu gasesc ComandaCap cu capId: "+capId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/ComandaCap")
    public List<ComandaCap> findComandaCapAll(){
        return comandaCapService.findAll();
    }


}
