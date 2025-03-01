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
import ro.papetti.LivrariTabele.entity.Masina;
import ro.papetti.livrari.liv.services.MasinaService;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/")
public class MasinaRestController {

    private final MasinaService masinaService;


    @GetMapping("/Masina/{idMasina}")
    public ResponseEntity<Masina> findMasinaById(@NonNull @PathVariable int idMasina){
        Masina entity = masinaService.findById(idMasina)
                .orElseThrow(()-> new EntityNotFoundException("Nu gasesc Masina cu idMasina: "+idMasina));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Masina")
    public List<Masina> findMasinaAll(){
        return masinaService.findAll();
    }
}
