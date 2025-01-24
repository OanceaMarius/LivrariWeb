/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controllers.rest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.PlurivaTabele.entity.SOrderCap;
import ro.papetti.PlurivaTabele.entity.SOrderPoz;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.livrari.plu.services.TblTipLivrareService;

/**
 *
 * @author MariusO
 */
@RestController
@RequestMapping("/api/pluriva")
public class PlurivaRestController {
    
    private final SOrderCapService sOrderCapSer;
    private final SOrderPozService sOrderPozSer;
    private final TblTipLivrareService tipLivrare;

    public PlurivaRestController(ro.papetti.livrari.plu.services.SOrderPozService sOrderPozSer, ro.papetti.livrari.plu.services.TblTipLivrareService tipLivrare, ro.papetti.livrari.plu.services.SOrderCapService sOrderCapSer) {
        this.sOrderPozSer = sOrderPozSer;
        this.tipLivrare = tipLivrare;
        this.sOrderCapSer = sOrderCapSer;
    }
    
    @GetMapping("/SorderCap/{sOrderCapId}")
    public SOrderCap findSOrderCapById(@PathVariable int sOrderCapId){
        return sOrderCapSer.findById(sOrderCapId).get();
    }
    
    @GetMapping("/SorderPoz/ByCapId/{sOrderCapId}")
    public List<SOrderPoz> findSOrderPozByCapId(@PathVariable int sOrderCapId){
        return sOrderPozSer.findPozitiiBySOrderCapId(sOrderCapId);
    }
    
}
