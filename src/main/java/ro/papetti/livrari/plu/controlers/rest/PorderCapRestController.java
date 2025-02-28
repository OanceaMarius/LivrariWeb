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
import ro.papetti.livrari.plu.services.PorderCapService;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.entity.PorderCap;


@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class PorderCapRestController {
    private final  PorderCapService porderCapService;


    @GetMapping("/PorderCap/{porderCapId}")
    public ResponseEntity<PorderCap> findProrderCapEagerById(@NonNull @PathVariable int porderCapId) {
        PorderCap entity = porderCapService.findEagerById(porderCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc PorderCap cu porderCapId: " + porderCapId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/PorderCapDTO/{porderCapId}")
    public ResponseEntity<PorderCapDto> findPorderCapDtoEagerById(@NonNull @PathVariable int porderCapId) {
        PorderCapDto entity = porderCapService.findDtoById(porderCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc PorderCapDto cu sorderCapId: " + porderCapId));
        return ResponseEntity.ok(entity);
    }

}
