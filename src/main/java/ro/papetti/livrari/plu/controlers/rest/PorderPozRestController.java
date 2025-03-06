package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.PorderPozService;
import ro.papetti.pluriva.dto.PorderPozDto;
import ro.papetti.pluriva.entity.PorderPoz;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class PorderPozRestController {

    private final PorderPozService porderPozService;

    @GetMapping("/PorderPozDTO/CapId/{porderCapId}")
    public List<PorderPozDto> findPorderPozDtoByPorderCapId(@NonNull @PathVariable int porderCapId) {
        return porderPozService.findPozDtoByPOrderCapId(porderCapId);
    }

    @GetMapping("/PorderPoz/CapId/{porderCapId}")
    public List<PorderPoz> findPorderPozByPorderCapId(@NonNull @PathVariable int porderCapId) {
        return porderPozService.findEagerByPorderCapId(porderCapId);

    }

    @GetMapping("/PorderPoz/{porderPozId}")
    public ResponseEntity<PorderPoz> findProrderPozById(@NonNull @PathVariable int porderPozId) {
        PorderPoz entity = porderPozService.findEagerById(porderPozId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc PorderPoz cu porderPozId: " + porderPozId));
        return ResponseEntity.ok(entity);
    }

}
