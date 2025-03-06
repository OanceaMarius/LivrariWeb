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
import ro.papetti.LivrariTabele.entity.CoordonateFixe;
import ro.papetti.livrari.liv.services.CoordonateFixeService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CoordonateFixeRestController {

    private final CoordonateFixeService coordonateFixeService;

    @GetMapping("/CoordonateFixe/{idCoordonata}")
    public ResponseEntity<CoordonateFixe> findCoordonateFixeById(@NonNull @PathVariable int idCoordonata){
        CoordonateFixe entity = coordonateFixeService.findById(idCoordonata)
                .orElseThrow(()-> new EntityNotFoundException("Nu gasesc CoordonateFixe cu idCoordonata: "+idCoordonata));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/CoordonateFixe")
    public List<CoordonateFixe>findCoordonateFixeAll(){
        return coordonateFixeService.findAll();
    }

}
