package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.IntrCapService;
import ro.papetti.pluriva.dto.IntrCapDto;
import ro.papetti.pluriva.entity.IntrCap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class IntrCapRestController {
    private final IntrCapService intrCapService;

    @GetMapping("/IntrCap/{intrCapId}")
    public ResponseEntity<IntrCap> findIntrCapEagerById(@NonNull @PathVariable int intrCapId) {
        IntrCap entity = intrCapService.findEagerById(intrCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc IntrCap cu intrCapId: " + intrCapId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/IntrCapDTO/{intrCapId}")
    public ResponseEntity<IntrCapDto> findIntrCapDtoById(@NonNull @PathVariable int intrCapId) {
        IntrCapDto entity = intrCapService.findDtoById(intrCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc IntrCapDto cu intrCapId: " + intrCapId));
        return ResponseEntity.ok(entity);
    }

}
