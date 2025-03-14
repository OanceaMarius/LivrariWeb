package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.LimitePartenerClientService;
import ro.papetti.pluriva.entity.LimitePartenerClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class LimitePartenerClientRestController {

    private final LimitePartenerClientService limitePartenerClientService;

    @GetMapping("/LimitePartenerClient")
    public List<LimitePartenerClient> findLimitePartenerClientAll(){
        return limitePartenerClientService.findEagerAll();
    }

    @GetMapping("/LimitePartenerClient/{partenerFirmaId}/{partenerId}")
    public ResponseEntity<LimitePartenerClient> findLimitePartenerClientByIdWithUnitate_1(@PathVariable int partenerFirmaId, @PathVariable int partenerId){
        LimitePartenerClient entity = limitePartenerClientService.findEagerByIdWithDivizie_1(partenerFirmaId, partenerId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc LimitePartenerClient cu partenerFirmaId: " + partenerFirmaId + " si partenerId: " + partenerId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/LimiteClient/{firmaId}/{clientId}")
    public ResponseEntity<LimitePartenerClient> findLimiteClientByIdWithUnitate_1(@PathVariable int firmaId, @PathVariable int clientId){
        LimitePartenerClient entity = limitePartenerClientService.findEagerByClientIdWithDivizie_1(firmaId, clientId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc LimitePartenerClient cu firmaId: " + firmaId + " si clientId: " + clientId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/LimiteClientBlocat/{firmaId}/{clientId}")
    public boolean islientBlocatByIdWithUnitate_1(@PathVariable int firmaId, @PathVariable int clientId){
        return limitePartenerClientService.isBlocat(firmaId, clientId);

    }





}
