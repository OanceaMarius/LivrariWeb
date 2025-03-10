package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.GestiuneService;
import ro.papetti.pluriva.dto.GestiuneDto;
import ro.papetti.pluriva.entity.Gestiune;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class GestiuneRestController {
    private final GestiuneService gestiuneService;

    @GetMapping("/Gestiune")
    public List<Gestiune> findGestiuneAll() {
        return gestiuneService.findAll();
    }

    @GetMapping("/GestiuneDTO")
    public List<GestiuneDto> findGestiuneDtoAll() {
        return gestiuneService.findDtoAll();
    }

    @GetMapping("/GestiuneDTO/{gestiuneId}")
    public ResponseEntity<GestiuneDto> findGestiuneDtoById(@NonNull @PathVariable int gestiuneId) {
        GestiuneDto entity = gestiuneService.findDtoById(gestiuneId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc GestiuneDto cu gestiuneId: " + gestiuneId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Gestiune/{gestiuneId}")
    public ResponseEntity<Gestiune> findGestiuneById(@NonNull @PathVariable int gestiuneId) {
        Gestiune entity = gestiuneService.findById(gestiuneId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc Gestiune cu gestiuneId: " + gestiuneId));
        return ResponseEntity.ok(entity);
    }
}
