package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.ModPlataService;
import ro.papetti.pluriva.dto.ModPlataDTOI;
import ro.papetti.pluriva.entity.ModPlata;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class ModPlataRestController {
    private final ModPlataService modPlataService;

    public ModPlataRestController(ModPlataService modPlataService) {
        this.modPlataService = modPlataService;
    }


    @GetMapping("/ModPlata")
    public List<ModPlata> findModPlataAll(){
        return modPlataService.findAll();
    }

    @GetMapping("/ModPlataDTO")
    public List<ModPlataDTOI> findModPlataDTOAll(){
        return modPlataService.findDTOAll(ModPlataDTOI.class);
    }

    @GetMapping("/ModPlataDTO/{modPlataId}")
    public ResponseEntity<ModPlataDTOI> findModPlataDTOById(@NonNull @PathVariable int modPlataId){
        ModPlataDTOI entity = modPlataService.findDTOById(modPlataId, ModPlataDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc ModPlataDTO cu modPlataId: " + modPlataId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/ModPlata/{modPlataId}")
    public ResponseEntity<ModPlata> findModPlataById(@NonNull @PathVariable int modPlataId){
        ModPlata entity = modPlataService.findById(modPlataId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc ModPlata cu modPlataId: " + modPlataId));
        return ResponseEntity.ok(entity);
    }

}
