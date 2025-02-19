package ro.papetti.livrari.plu.controlers.rest;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.dtoi.UnitateDTOI;
import ro.papetti.pluriva.entity.Unitate;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class UnitateRestController {

    private final UnitateService unitateService;


    public UnitateRestController(UnitateService unitateService) {
        this.unitateService = unitateService;
    }


    @GetMapping("/Unitate")
    public List<Unitate> findUnitateAll(){
        return unitateService.findAll();
    }

    @GetMapping("/UnitateDTO")
    public List<UnitateDTOI> findUnitateDTOAll(){
        return unitateService.findDTOAll(UnitateDTOI.class);
    }

    @GetMapping("/UnitateDTO/{unitateID}")
    public ResponseEntity<UnitateDTOI> findUnitateDTOById(@NonNull @PathVariable int unitateID){
        UnitateDTOI entity = unitateService.findDTOById(unitateID,UnitateDTOI.class)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc UnitateDTO cu unitateID: " + unitateID));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Unitate/{unitateID}")
    public ResponseEntity<Unitate> findUnitateById(@NonNull @PathVariable int unitateID){
        Unitate entity = unitateService.findById(unitateID)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc Unitate cu unitateID: " + unitateID));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/UnitateDTO/denumire/{denumireUnitate}")
    public List<UnitateDTOI> findUnitateDTOByDenumireUnitate(@NonNull @PathVariable String denumireUnitate){
        List<UnitateDTOI> listUnitati = unitateService.findDTOByDenumireUnitate(denumireUnitate,UnitateDTOI.class);
        if (listUnitati.isEmpty())
                throw new EntityNotFoundException("Nu gasesc UnitateDTO cu denumireUnitate like: '%" + denumireUnitate +"%'");
        return listUnitati;
    }

    @GetMapping("/Unitate/denumire/{denumireUnitate}")
    public ResponseEntity<List<Unitate>> findUnitateByDenumireUnitate(@NonNull @PathVariable String denumireUnitate){
        List<Unitate> listUnitate = unitateService.findByDenumireUnitate(denumireUnitate);
        if (listUnitate.isEmpty())
                throw new EntityNotFoundException("Nu gasesc Unitate cu denumireUnitate like: '%" + denumireUnitate+"%'");
        return ResponseEntity.ok(listUnitate);
    }

}
