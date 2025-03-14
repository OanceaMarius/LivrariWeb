package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.TipFurnizorService;
import ro.papetti.pluriva.dto.TipFurnizorDto;
import ro.papetti.pluriva.entity.TipFurnizor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class TipFurnizorRestController {

    private final TipFurnizorService tipFurnizorService;

    @GetMapping("/TipFurnizor")
    public List<TipFurnizor> findTipFurnizorAll(){
        return tipFurnizorService.findAll();
    }


    @GetMapping("/TipFurnizor/{tipFurnizorId}")
    public ResponseEntity<TipFurnizor> findTipFirmaById(@NonNull @PathVariable int tipFurnizorId){
        TipFurnizor entity = tipFurnizorService.findById(tipFurnizorId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipFurnizor cu tipFurnizorId: " + tipFurnizorId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TipFurnizorDTO")
    public List<TipFurnizorDto> findTipFurnizorDtoAll(){
        return tipFurnizorService.findDtoAll();
    }


    @GetMapping("/TipFurnizorDTO/{tipFurnizorId}")
    public ResponseEntity<TipFurnizorDto> findTipFirmaDtoById(@NonNull @PathVariable int tipFurnizorId){
        TipFurnizorDto entity = tipFurnizorService.findDtoById(tipFurnizorId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipFurnizor cu tipFurnizorId: " + tipFurnizorId));
        return ResponseEntity.ok(entity);
    }


}
