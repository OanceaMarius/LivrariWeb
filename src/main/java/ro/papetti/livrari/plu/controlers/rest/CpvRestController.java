package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.CpvService;
import ro.papetti.pluriva.entity.Cpv;

import java.util.List;


@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class CpvRestController {
    private final CpvService cpvService;

    public CpvRestController(CpvService cpvService) {
        this.cpvService = cpvService;
    }

    @GetMapping("/Cpv")
    public List<Cpv> findCpvAll() {
        return cpvService.findAll();
    }


    @GetMapping("/Cpv/{cpvId}")
    public ResponseEntity<Cpv> findCpvById(@NonNull @PathVariable int cpvId) {
        Cpv entity = cpvService.findById(cpvId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc CPV cu cpvId: "+ cpvId));
        return ResponseEntity.ok(entity);
    }
}
