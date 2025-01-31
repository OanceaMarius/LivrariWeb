/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controllers.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.POrderCapService;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.livrari.plu.services.TipLivrareService;
import ro.papetti.livrari.plu.services.UnitateService;
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

    public PlurivaRestController(
            SOrderPozService sOrderPozSer, 
            TipLivrareService tipLivrare, 
            ro.papetti.livrari.plu.services.SOrderCapService sOrderCapSer, 
            UnitateService unitateSer,
            POrderCapService pOrderCapSer,
            POrderPozService pOrderPozSer
            ) {
        this.sOrderPozSer = sOrderPozSer;
        this.tipLivrareSer = tipLivrare;
        this.sOrderCapSer = sOrderCapSer;
        this.unitateSer=unitateSer;
        this.pOrderCapSer=pOrderCapSer;
        this.pOrderPozSer=pOrderPozSer;
    }
    
    @GetMapping("/SOrderCap/{sOrderCapId}")
    public SOrderCap findSOrderCapById(@PathVariable int sOrderCapId){
        return sOrderCapSer.findById(sOrderCapId).get();
    }
    
    @GetMapping("/SOrderCap/ByDataLivrarii/{dataLivrarii}")
    public List<SOrderCap> findSOrderCapByDataLivrarii(@PathVariable Date dataLivrarii){
        return sOrderCapSer.findByDataLivrare(dataLivrarii);
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

    
    
    
    @GetMapping("/tipLivrari")
    public List<TipLivrare> findAllTipLivrari(){
        return tipLivrareSer.findAll();
    }
    
    
    
}
