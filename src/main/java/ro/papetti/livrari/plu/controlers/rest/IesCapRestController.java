package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.IesCapService;
import ro.papetti.livrari.plu.services.IntrCapService;
import ro.papetti.pluriva.dto.IesCapDto;
import ro.papetti.pluriva.dto.IntrCapDto;
import ro.papetti.pluriva.entity.IesCap;
import ro.papetti.pluriva.entity.IntrCap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class IesCapRestController {
    private final IesCapService iesCapService;

    @GetMapping("/IesCap/{iesCapId}")
    public ResponseEntity<IesCap> findIesCapEagerById(@NonNull @PathVariable int iesCapId) {
        IesCap entity = iesCapService.findEagerById(iesCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc IesCap cu iesCapId: " + iesCapId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/IesCapDTO/{iesCapId}")
    public ResponseEntity<IesCapDto> findIntrCapDtoById(@NonNull @PathVariable int iesCapId) {
        IesCapDto entity = iesCapService.findDtoById(iesCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc IesCapDto cu iesCapId: " + iesCapId));
        return ResponseEntity.ok(entity);
    }

}
