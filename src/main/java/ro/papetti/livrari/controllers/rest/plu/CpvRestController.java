package ro.papetti.livrari.controllers.rest.plu;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.CPVService;
import ro.papetti.pluriva.entity.CPV;

import java.util.List;


@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class CpvRestController {
    private final CPVService cpvService;

    public CpvRestController(CPVService cpvService) {
        this.cpvService = cpvService;
    }

    @GetMapping("/Cpv")
    public List<CPV> findCpvAll() {
        return cpvService.findAll();
    }

    //NU ARE DTO CA NU E NEVOIE
//    @GetMapping("/CpvDTO")
//    public List<UmDTOI> findTvaDTOAll(){
//        return cpvService.findDTOAll(UmDTOI.class);
//    }

//    @GetMapping("/UmDTO/{umId}")
//    public Optional<UmDTOI> findUmDTOById(@NonNull @PathVariable int umId){
//        return cpvService.findDTOById(umId,UmDTOI.class);
//    }

    @GetMapping("/Cpv/{cpvId}")
    public ResponseEntity<CPV> findCpvById(@NonNull @PathVariable int cpvId) {
        CPV entity = cpvService.findById(cpvId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc CPV cu cpvId: "+ cpvId));
        return ResponseEntity.ok(entity);
    }
}
