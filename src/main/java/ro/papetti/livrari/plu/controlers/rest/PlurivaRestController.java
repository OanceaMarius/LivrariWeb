/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dtoi.*;
import ro.papetti.pluriva.entity.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author MariusO
 */
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class PlurivaRestController {
    
    //ca sa introduc data in formatul dorit de mine
    @InitBinder
    public void initBinder(@org.jetbrains.annotations.NotNull WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    
    private final SorderCapService sorderCapService;
    private final SorderPozService sorderPozService;
    private final PorderCapService porderCapService;
    private final PorderPozService porderPozService;
    private final StocService stocService;


    @GetMapping("/SOrderCap/{sOrderCapId}")
    public ResponseEntity<SorderCap> findSOrderCapById(@PathVariable int sOrderCapId){
        SorderCap cap = sorderCapService.findById(sOrderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc SOrderCap cu SorderId: "+sOrderCapId));
        return ResponseEntity.ok(cap);
    }
    
    @GetMapping("/SOrderCapDTO/{sOrderCapId}")
    public ResponseEntity<SorderCapDTOI> findDTOById(@PathVariable int sOrderCapId){
        SorderCapDTOI cap = sorderCapService.findDTOIById(sOrderCapId).orElseThrow(()->new EntityNotFoundException("Nu gasesc SOrderCap cu SorderId: "+sOrderCapId));
        return ResponseEntity.ok(cap);
    }

    /**
     * 
     * @param dataLivrarii in format yyyy-mm-dd
     * @return 
     */
    @GetMapping("/SOrderCap/ByDataLivrarii/{dataLivrarii}")
    public List<SorderCap> findSOrderCapByDataLivrarii(@PathVariable Date dataLivrarii){
        return sorderCapService.findByDataLivrare(dataLivrarii).orElse(new ArrayList<SorderCap>());
    }

    @Transactional
    @GetMapping("/SOrderPozDTO/ByCapId/{sOrderCapId}")
    public List<SorderPozDTOI> findSOrderPozDTOByCapId(@PathVariable int sOrderCapId){
        return sorderPozService.findPozitiiDTOIBySOrderCapId(sOrderCapId);
    }
    
       @Transactional
    @GetMapping("/SOrderPoz/ByCapId/{sOrderCapId}")
    public List<SorderPoz> findSOrderPozByCapId(@PathVariable int sOrderCapId){
        return sorderPozService.findPozitiiBySOrderCapId(sOrderCapId);
    }
    
    @GetMapping("/POrderPozDTO/ByCapId/{pOrderCapId}")
    public List<PorderPozDTOI> findSOrderPozDTOBySOrderCapId(@PathVariable int pOrderCapId){
        return porderPozService.findPozDTOIByPOrderCapId(pOrderCapId, PorderPozDTOI.class);
    }
    
    
    @GetMapping("/POrderPoz/ByCapId/{pOrderCapId}")
    public List<PorderPoz> findPOrderPozByCapId(@PathVariable int pOrderCapId){
        return porderPozService.findEagerByPorderCapId(pOrderCapId);
    }
    
    //studiu
    @Transactional
    @GetMapping("/POrderPozByCapId/{pOrderCapId}")
    public List<PorderPoz> findPOrderPoz_CapId(@PathVariable int pOrderCapId){
        return  porderCapService.findPOrderPozByPOrderCapId(pOrderCapId);


    }
    
    @GetMapping("/POrderCap/{pOrderCapId}")
    public ResponseEntity<PorderCap> findPOrderCapById(@PathVariable int pOrderCapId){
        PorderCap cap =  porderCapService.findById(pOrderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc POrderCap cu POrderCapId: "+pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
        return ResponseEntity.ok(cap);
    }
    
        @GetMapping("/POrderCapDTO/{pOrderCapId}")
    public ResponseEntity<PorderCapDTOI> findDTOByPOrderCapId(@PathVariable int pOrderCapId){
        PorderCapDTOI cap =  porderCapService.findDTOIByPOrderCapId(pOrderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc POrderCap cu POrderCapId: "+pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
            Hibernate.initialize(cap.getFurnizorUnitate());
        return ResponseEntity.ok(cap);
    }

    @GetMapping("/POrderCapDTO2/{porderCapId}")
    public ResponseEntity<PorderCapDto> findDtoPorderCapById(@PathVariable int porderCapId){
        PorderCapDto cap =  porderCapService.findDtoById(porderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc POrderCap cu POrderCapId: "+porderCapId));
//        Hibernate.initialize(cap.getPozitii());
//        Hibernate.initialize(cap.getFurnizorUnitate());
        return ResponseEntity.ok(cap);
    }


    @GetMapping("/POrderCap/ByDataLivrarii/{dataLivrarii}")
    public List<PorderCap> findPOrderCapByDataLivrarii(@PathVariable Date dataLivrarii){
        return porderCapService.findByDataLivrare(dataLivrarii);
    }
    

    @GetMapping("/StocDisponibil/{firmaId}/{gestiuneId}")
    public List<StocDisponibil> getStocDisponibil(@PathVariable int firmaId, @PathVariable int gestiuneId){
        return stocService.getStocDisponibilInGestiune(firmaId, gestiuneId);
    }
    
    @GetMapping("/StocDisponibil/{firmaId}")
    public Set<StocDisponibil>  getStocDisponibilOperational(@PathVariable int firmaId){
        return stocService.getStocDisponibilInGestiuneOperationala(firmaId);
    }
    
    @GetMapping("/SCantLivrate/{sOrderCapId}/{firmaId}")
    public List<PozCantitate>  getCantitatiLivrate(@PathVariable int sOrderCapId, @PathVariable int firmaId){
        return sorderCapService.getCantitatiLivrate(sOrderCapId, firmaId);
    }
    
    @GetMapping("/SCantRezervate/{sOrderCapId}")
    public List<PozCantitate>  getCantitatiRezervate(@PathVariable int sOrderCapId){
        return sorderCapService.getCantitatiRezervate(sOrderCapId);
    }


}
