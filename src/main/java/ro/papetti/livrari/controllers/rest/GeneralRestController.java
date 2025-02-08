/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controllers.rest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.liv.services.jpa.ComandaCapServiceImpl;
import ro.papetti.livrari.liv.services.jpa.ComandaHartaServiceImpl;
import ro.papetti.livrari.liv.services.jpa.ComandaPozServiceImpl;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.plu.services.jpa.POrderCapServiceImpl;
import ro.papetti.livrari.plu.services.jpa.POrderPozServiceImpl;
import ro.papetti.livrari.plu.services.jpa.SOrderCapServiceImpl;
import ro.papetti.livrari.plu.services.jpa.SOrderPozServiceImpl;



/**
 *
 * @author MariusO
 */
@RestController
@Transactional
@RequestMapping("/api")
public class GeneralRestController {
        
    private final ComandaCapServiceImpl capSer;
    private final ComandaPozServiceImpl liniiSer;
    private final SOrderCapServiceImpl sCapPluService;
    private final SOrderPozServiceImpl sLiniiPluService;
    private final POrderCapServiceImpl pCapPluService;
    private final POrderPozServiceImpl pLiniiPluService;

    private final ComandaHartaServiceImpl hartaService;


    
    public GeneralRestController(ComandaCapServiceImpl capSer, ComandaPozServiceImpl liniiSer, SOrderCapServiceImpl sCapPluService, SOrderPozServiceImpl sLiniiPluService, POrderCapServiceImpl pCapPluService, POrderPozServiceImpl pLiniiPluService, ComandaHartaServiceImpl hartaService){
        this.capSer = capSer;
        this.liniiSer = liniiSer;
        this.sCapPluService = sCapPluService;
        this.sLiniiPluService = sLiniiPluService;
        this.pCapPluService = pCapPluService;
        this.pLiniiPluService = pLiniiPluService;

        this.hartaService = hartaService;
        

    }
    @Transactional
    @GetMapping(value = "/ComandaHarta/{capId}")      
    public ComandaHarta getComadaCapById(@PathVariable int capId) {
        return hartaService.getComandaHartaById(capId);
    }
//    @GetMapping("/liniiProgram/proc/{nr}")
//    public List<String> getProcedure(@PathVariable int nr){
//        return liniiProSer.getTestProc(nr);
//    }
}
