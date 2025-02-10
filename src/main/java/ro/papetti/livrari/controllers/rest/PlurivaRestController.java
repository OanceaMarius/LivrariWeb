/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controllers.rest;

import jakarta.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.services.FollowUpService;
import ro.papetti.livrari.plu.services.POrderCapService;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.livrari.plu.services.StocService;
import ro.papetti.pluriva.dto.SOrderPozDTOI;
import ro.papetti.pluriva.entity.FollowUp;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.SOrderCap;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class PlurivaRestController {
    
    //ca sa introduc data in formatul dorit de mine
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    
    private final SOrderCapService sOrderCapSer;
    private final SOrderPozService sOrderPozSer;
    private final POrderCapService pOrderCapSer;
    private final POrderPozService pOrderPozSer;    
    private final FollowUpService follwUpSer;
    private final StocService stocService;

    public PlurivaRestController(
            SOrderPozService sOrderPozSer, SOrderCapService sOrderCapSer, 
            POrderCapService pOrderCapSer, POrderPozService pOrderPozSer, 
            FollowUpService follwUpSer, StocService stocService) {
        this.sOrderPozSer = sOrderPozSer;
        this.sOrderCapSer = sOrderCapSer;
        this.pOrderCapSer=pOrderCapSer;
        this.pOrderPozSer=pOrderPozSer;
        this.follwUpSer=follwUpSer;
        this.stocService = stocService;
    }
    
    @GetMapping("/SOrderCap/{sOrderCapId}")
    public ResponseEntity<SOrderCap> findSOrderCapById(@PathVariable int sOrderCapId){
        SOrderCap cap = sOrderCapSer.findById(sOrderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc SOrderCap cu SorderId: "+sOrderCapId));
        return ResponseEntity.ok(cap);
    }
    

    /**
     * 
     * @param dataLivrarii in format yyyy-mm-dd
     * @return 
     */
    @GetMapping("/SOrderCap/ByDataLivrarii/{dataLivrarii}")
    public List<SOrderCap> findSOrderCapByDataLivrarii(@PathVariable Date dataLivrarii){
        return sOrderCapSer.findByDataLivrare(dataLivrarii).orElse(new ArrayList());
    }

    @Transactional
    @GetMapping("/SOrderPozDTO/ByCapId/{sOrderCapId}")
    public List<SOrderPozDTOI> findSOrderPozDTOByCapId(@PathVariable int sOrderCapId){
        return sOrderPozSer.findPozitiiDTOBySOrderCapId(sOrderCapId);
    }
    
       @Transactional
    @GetMapping("/SOrderPoz/ByCapId/{sOrderCapId}")
    public List<SOrderPoz> findSOrderPozByCapId(@PathVariable int sOrderCapId){
        return sOrderPozSer.findPozitiiBySOrderCapId(sOrderCapId);
    }
    
    
    @GetMapping("/POrderPoz/ByCapId/{pOrderCapId}")
    public List<POrderPoz> findPOrderPozByCapId(@PathVariable int pOrderCapId){
        return pOrderPozSer.findPozitiiByPOrderCapId(pOrderCapId);
    }
    
    //studiu
    @Transactional
    @GetMapping("/POrderPozByCapId/{pOrderCapId}")
    public List<POrderPoz> findPOrderPoz_CapId(@PathVariable int pOrderCapId){
        return  pOrderCapSer.findPOrderPozByPOrderCapId(pOrderCapId);


    }
    
    @GetMapping("/POrderCap/{pOrderCapId}")
    public ResponseEntity<POrderCap> findPOrderCapById(@PathVariable int pOrderCapId){
        POrderCap cap =  pOrderCapSer.findById(pOrderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc POrderCap cu POrderCapId: "+pOrderCapId));
        return ResponseEntity.ok(cap);
    }


    @GetMapping("/POrderCap/ByDataLivrarii/{dataLivrarii}")
    public List<POrderCap> findPOrderCapByDataLivrarii(@PathVariable Date dataLivrarii){
        return pOrderCapSer.findByDataLivrare(dataLivrarii);
    }
    
    

    
    @GetMapping("/followUp/{followUpId}")
    public Optional<FollowUp> findFollowUpById(@PathVariable int followUpId){
        return follwUpSer.findById(followUpId);
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
        return sOrderCapSer.getCantitatiLivrate(sOrderCapId, firmaId);
    }
    
    @GetMapping("/SCantRezervate/{sOrderCapId}")
    public List<PozCantitate>  getCantitatiRezervate(@PathVariable int sOrderCapId){
        return sOrderCapSer.getCantitatiRezervate(sOrderCapId);
    }
}
