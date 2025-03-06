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
import ro.papetti.livrari.plu.services.CpvService;
import ro.papetti.pluriva.dto.CpvDto;
import ro.papetti.pluriva.entity.Cpv;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class CpvRestController {
    private final CpvService cpvService;


    @GetMapping("/Cpv")
    public List<Cpv> findCpvAll() {
        return cpvService.findAll();
    }


    @GetMapping("/Cpv/{cpvId}")
    public ResponseEntity<Cpv> findCpvById(@NonNull @PathVariable int cpvId) {
        Cpv entity = cpvService.findById(cpvId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Cpv cu cpvId: "+ cpvId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/CpvDTO")
    public List<CpvDto> findCpvDtoAll() {
        return cpvService.findDtoAll();
    }


    @GetMapping("/CpvDTO/{cpvId}")
    public ResponseEntity<CpvDto> findCpvDtoById(@NonNull @PathVariable int cpvId) {
        CpvDto entity = cpvService.findDtoById(cpvId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc CpvDto cu cpvId: "+ cpvId));
        return ResponseEntity.ok(entity);
    }
}
