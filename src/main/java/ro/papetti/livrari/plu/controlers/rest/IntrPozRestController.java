package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.IntrPozService;
import ro.papetti.pluriva.dto.IntrPozDto;
import ro.papetti.pluriva.entity.IntrPoz;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class IntrPozRestController {
    private final IntrPozService intrPozService;


    @GetMapping("/IntrPoz/{intrPozId}")
    public ResponseEntity<IntrPoz> findIntrPozEagerById(@NonNull @PathVariable int intrPozId) {
        IntrPoz entity = intrPozService.findEagerById(intrPozId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc IntrPoz cu intrPozId: " + intrPozId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/IntrPozDTO/{intrPozId}")
    public ResponseEntity<IntrPozDto> findIntrPozDtoById(@NonNull @PathVariable int intrPozId) {
        IntrPozDto entity = intrPozService.findDtoById(intrPozId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc IntrPozDto cu intrPozId: " + intrPozId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/IntrPoz/ByIntrCapId/{intrCapId}")
    public List<IntrPoz> findEagerByIntrCapId(@NonNull @PathVariable int intrCapId) {
        return intrPozService.findEagerByIntrCapId(intrCapId);
    }

    @GetMapping("/IntrPozDTO/ByIntrCapId/{intrCapId}")
    public List<IntrPozDto> findDtoByIntrCapId(@NonNull @PathVariable int intrCapId) {
        return intrPozService.findDtoByIntrCapId(intrCapId);
    }


}
