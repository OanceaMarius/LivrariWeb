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
import ro.papetti.livrari.plu.services.PartenerService;
import ro.papetti.pluriva.dto.PartenerDto;
import ro.papetti.pluriva.entity.Partener;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class PartenerRestController {

    private final PartenerService partenerService;

    @GetMapping("/Partener")
    public List<Partener> findPartenerAll(){
        return partenerService.findAll();
    }

    @GetMapping("/PartenerDTO")
    public List<PartenerDto> findPartenerDtoAll(){
        return partenerService.findDtoAll();
    }

    @GetMapping("/PartenerDTO/{partenerId}")
    public ResponseEntity<PartenerDto> findPartenerDtoById(@NonNull @PathVariable int partenerId){
        PartenerDto entity = partenerService.findDtoById(partenerId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc PartenerDto cu partenerId: "+partenerId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Partener/{partenerId}")
    public ResponseEntity<Partener> findPartenerById(@NonNull @PathVariable int partenerId){
        Partener entity = partenerService.findEagerByPartenerID(partenerId)
                .orElseThrow(()-> new EntityNotFoundException("Nu gasesc Partener cu partenerId: "+partenerId));
        return ResponseEntity.ok(entity);
    }
}
