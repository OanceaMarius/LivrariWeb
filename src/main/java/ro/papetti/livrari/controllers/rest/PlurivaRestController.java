/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controllers.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import ro.papetti.livrari.plu.services.TipLivrareService;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.entity.FollowUp;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.SOrderCap;
import ro.papetti.pluriva.entity.SOrderPoz;
import ro.papetti.pluriva.entity.TipLivrare;

/**
 *
 * @author MariusO
 */
@RestController
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
    private final TipLivrareService tipLivrareSer;
    private final UnitateService unitateSer;
    private final FollowUpService follwUpSer;

    public PlurivaRestController(
            SOrderPozService sOrderPozSer, 
            TipLivrareService tipLivrare, 
            ro.papetti.livrari.plu.services.SOrderCapService sOrderCapSer, 
            UnitateService unitateSer,
            POrderCapService pOrderCapSer,
            POrderPozService pOrderPozSer,
            FollowUpService follwUpSer

            ) {
        this.sOrderPozSer = sOrderPozSer;
        this.tipLivrareSer = tipLivrare;
        this.sOrderCapSer = sOrderCapSer;
        this.unitateSer=unitateSer;
        this.pOrderCapSer=pOrderCapSer;
        this.pOrderPozSer=pOrderPozSer;
        this.follwUpSer=follwUpSer;
    }
    
    @GetMapping("/SOrderCap/{sOrderCapId}")
    public SOrderCap findSOrderCapById(@PathVariable int sOrderCapId){
        return sOrderCapSer.findById(sOrderCapId).orElse(null);
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

    
    @GetMapping("/SOrderPoz/ByCapId/{sOrderCapId}")
    public List<SOrderPoz> findSOrderPozByCapId(@PathVariable int sOrderCapId){
        return sOrderPozSer.findPozitiiBySOrderCapId(sOrderCapId);
    }
    
    @GetMapping("/POrderPoz/ByCapId/{pOrderCapId}")
    public List<POrderPoz> findPOrderPozByCapId(@PathVariable int pOrderCapId){
        return pOrderPozSer.findPozitiiByPOrderCapId(pOrderCapId);
    }
    
    @GetMapping("/POrderCap/{pOrderCapId}")
    public POrderCap findPOrderCapByOrderCapId(@PathVariable int pOrderCapId){
//        return pOrderCapSer.findByPOrderCapId(pOrderCapId);
        return pOrderCapSer.findById(pOrderCapId).orElse(null);
    }


    @GetMapping("/POrderCap/ByDataLivrarii/{dataLivrarii}")
    public Optional<List<POrderCap>> findPOrderCapByDataLivrarii(@PathVariable Date dataLivrarii){
        return pOrderCapSer.findByDataLivrare(dataLivrarii);
    }
    
    
    @GetMapping("/tipLivrari")
    public List<TipLivrare> findAllTipLivrari(){
        return tipLivrareSer.findAll();
    }
    
    @GetMapping("/followUp/{followUpId}")
    public Optional<FollowUp> findFollowUpById(@PathVariable int followUpId){
        return follwUpSer.findById(followUpId);
    }
    
    @GetMapping("/StocDisponibil/{firmaId}/{gestiuneId}")
    public List<StocDisponibil> getStocDisponibil(@PathVariable int firmaId, @PathVariable int gestiuneId){
        return sOrderPozSer.getStocDisponibil(firmaId, gestiuneId);
    }
    
    @GetMapping("/StocDisponibil/{firmaId}")
    public List<StocDisponibil>  getStocDisponibilOperational(@PathVariable int firmaId){
        return sOrderPozSer.getStocDisponibilInGestiuneOperationala(firmaId);
    }
    
    @GetMapping("/SCantLivrate/{sOrderCapId}/{firmaId}")
    public List<PozCantitate>  getCantitatiLivrate(@PathVariable int sOrderCapId, @PathVariable int firmaId){
        return sOrderPozSer.getCantitatiLivrate(sOrderCapId, firmaId);
    }
    
    @GetMapping("/SCantRezervate/{sOrderCapId}")
    public List<PozCantitate>  getCantitatiRezervate(@PathVariable int sOrderCapId){
        return sOrderPozSer.getCantitatiRezervate(sOrderCapId);
    }
}
