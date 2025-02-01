/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.liv.services.ComandaHartaService;
import ro.papetti.livrari.liv.services.ComandaPozService;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.plu.services.POrderCapService;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.livrari.plu.services.UnitateService;


/**
 *
 * @author MariusO
 */
@RestController
@RequestMapping("/api")
public class GeneralRestController {
        
    private final ComandaCapService capSer;
    private final ComandaPozService liniiSer;
    private final SOrderCapService sCapPluService;
    private final SOrderPozService sLiniiPluService;
    private final POrderCapService pCapPluService;
    private final POrderPozService pLiniiPluService;
    private final UnitateService tblUnitateService;
    private final ComandaHartaService hartaService;


    
    public GeneralRestController(ComandaCapService capSer, ComandaPozService liniiSer, SOrderCapService sCapPluService, SOrderPozService sLiniiPluService, POrderCapService pCapPluService, POrderPozService pLiniiPluService, UnitateService tblUnitateService, ComandaHartaService hartaService){
        this.capSer = capSer;
        this.liniiSer = liniiSer;
        this.sCapPluService = sCapPluService;
        this.sLiniiPluService = sLiniiPluService;
        this.pCapPluService = pCapPluService;
        this.pLiniiPluService = pLiniiPluService;
        this.tblUnitateService = tblUnitateService;
        this.hartaService = hartaService;
        

    }
    
    @GetMapping(value = "/ComandaHarta/{capId}")      
    public ComandaHarta getComadaCapById(@PathVariable int capId) {
        return hartaService.getComandaHartaById(capId);
    }
//    @GetMapping("/liniiProgram/proc/{nr}")
//    public List<String> getProcedure(@PathVariable int nr){
//        return liniiProSer.getTestProc(nr);
//    }
}
