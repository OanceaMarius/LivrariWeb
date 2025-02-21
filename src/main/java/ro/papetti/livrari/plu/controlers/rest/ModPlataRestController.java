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
import ro.papetti.livrari.plu.services.ModPlataService;
import ro.papetti.pluriva.dto.ModPlataDto;
import ro.papetti.pluriva.dtoi.ModPlataDTOI;
import ro.papetti.pluriva.entity.ModPlata;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class ModPlataRestController {
    private final ModPlataService modPlataService;


    @GetMapping("/ModPlata")
    public List<ModPlata> findModPlataAll(){
        return modPlataService.findAll();
    }

    @GetMapping("/ModPlataDTO")
    public List<ModPlataDto> findModPlataDtoAll(){
        return modPlataService.findDtoAll();
    }

    @GetMapping("/ModPlataDTO/{modPlataId}")
    public ResponseEntity<ModPlataDto> findModPlataDtoById(@NonNull @PathVariable int modPlataId){
        ModPlataDto entity = modPlataService.findDtoById(modPlataId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc ModPlataDto cu modPlataId: " + modPlataId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/ModPlata/{modPlataId}")
    public ResponseEntity<ModPlata> findModPlataById(@NonNull @PathVariable int modPlataId){
        ModPlata entity = modPlataService.findById(modPlataId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc ModPlata cu modPlataId: " + modPlataId));
        return ResponseEntity.ok(entity);
    }

}
