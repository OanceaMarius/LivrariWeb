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
import ro.papetti.livrari.plu.services.TipDocService;
import ro.papetti.pluriva.dto.TipDocDto;
import ro.papetti.pluriva.dtoi.TipDocDTOI;
import ro.papetti.pluriva.entity.TipDoc;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class TipDocRestController {

    private final TipDocService tipDocService;


    @GetMapping("/TipDoc")
    public List<TipDoc> findTipDocAll(){
        return tipDocService.findAll();
    }

    @GetMapping({"/TipDocDTO","/TipDocDTO/"})
    public List<TipDocDto> findTipDocDtoAll(){
        return tipDocService.findDtoAll();
    }

    @GetMapping("/TipDocDTO/{tipDocId}")
    public ResponseEntity<TipDocDto> findTipDocDtoById(@NonNull @PathVariable int tipDocId){
        TipDocDto entity = tipDocService.findDtoById((tipDocId))
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipDocDto cu tipDocId: " + tipDocId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/TipDoc/{tipDocId}")
    public ResponseEntity<TipDoc> findTipDocById(@NonNull @PathVariable int tipDocId){
        TipDoc entity = tipDocService.findById(tipDocId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc TipDoc cu tipDocId: " + tipDocId));
        return ResponseEntity.ok(entity);
    }


}
