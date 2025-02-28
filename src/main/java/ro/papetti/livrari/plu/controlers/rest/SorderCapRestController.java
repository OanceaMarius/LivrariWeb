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
import ro.papetti.livrari.plu.services.SorderCapService;
import ro.papetti.livrari.plu.services.SorderPozService;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.entity.SorderCap;

import java.util.Optional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class SorderCapRestController {
    private final SorderCapService sorderCapService;


    @GetMapping("/SorderCap/{sorderCapId}")
    public ResponseEntity<SorderCap> findSorderCapEagerById(@NonNull @PathVariable int sorderCapId) {
        SorderCap entity = sorderCapService.findEagerById(sorderCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc PorderCap cu sorderCapId: " + sorderCapId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/SorderCapDTO/{sorderCapId}")
    public ResponseEntity<SorderCapDto> findSorderCapDtoEagerById(@NonNull @PathVariable int sorderCapId) {
        SorderCapDto entity = sorderCapService.findDtoById(sorderCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc SorderCapDto cu sorderCapId: " + sorderCapId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/SorderCapDTO/PozId/{sorderPozId}")
    public Optional<SorderCapDto> findSorderCapDtoBySorderPozId(@NonNull @PathVariable int sorderPozId) {
        return sorderCapService.findSorderCapFaraPozitiiDtoBySorderPozId(sorderPozId);
    }

}
