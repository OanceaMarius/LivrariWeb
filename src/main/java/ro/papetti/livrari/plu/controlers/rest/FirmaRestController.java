package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.FirmaService;
import ro.papetti.pluriva.dto.FirmaDto;
import ro.papetti.pluriva.entity.Firma;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class FirmaRestController {
    private final FirmaService firmaService;


    @GetMapping("/Firma")
    public List<Firma> findFirmaEagerAll() {
        return firmaService.findEagerAll();
    }


    @GetMapping("/Firma/{firmaId}")
    public ResponseEntity<Firma> findFirmaEagerById(@NonNull @PathVariable int firmaId) {
        Firma entity = firmaService.findEagerById(firmaId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Firma cu firmaId: "+ firmaId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/FirmaDTO")
    public List<FirmaDto> findFirmaDtoAll() {
        return firmaService.findDtoAll();
    }


    @GetMapping("/FirmaDTO/{firmaId}")
    public ResponseEntity<FirmaDto> findFirmaDtoById(@NonNull @PathVariable int firmaId) {
        FirmaDto entity = firmaService.findDtoById(firmaId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc FirmaDto cu firmaId: "+ firmaId));
        return ResponseEntity.ok(entity);
    }


}
