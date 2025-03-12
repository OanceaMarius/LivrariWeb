package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.IesPozService;
import ro.papetti.livrari.plu.services.IntrPozService;
import ro.papetti.pluriva.dto.IesPozDto;
import ro.papetti.pluriva.dto.IntrPozDto;
import ro.papetti.pluriva.entity.IesPoz;
import ro.papetti.pluriva.entity.IntrPoz;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class IesPozRestController {
    private final IesPozService iesPozService;


    @GetMapping("/IesPoz/{iesPozId}")
    public ResponseEntity<IesPoz> findIesPozEagerById(@NonNull @PathVariable int iesPozId) {
        IesPoz entity = iesPozService.findEagerById(iesPozId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc IesPoz cu iesPozId: " + iesPozId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/IesPozDTO/{iesPozId}")
    public ResponseEntity<IesPozDto> findIesPozDtoById(@NonNull @PathVariable int iesPozId) {
        IesPozDto entity = iesPozService.findDtoById(iesPozId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc IesPozDto cu iesPozId: " + iesPozId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/IesPoz/ByIesCapId/{iesCapId}")
    public List<IesPoz> findEagerByIesCapId(@NonNull @PathVariable int iesCapId) {
        return iesPozService.findEagerByIesCapId(iesCapId);
    }

    @GetMapping("/IesPozDTO/ByIesCapId/{iesCapId}")
    public List<IesPozDto> findDtoByIesCapId(@NonNull @PathVariable int iesCapId) {
        return iesPozService.findDtoByIesCapId(iesCapId);
    }


}
