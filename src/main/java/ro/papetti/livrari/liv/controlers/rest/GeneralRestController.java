/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.liv.services.jpa.ComandaCapServiceImpl;
import ro.papetti.livrari.liv.services.jpa.ComandaHartaServiceImpl;
import ro.papetti.livrari.liv.services.jpa.ComandaPozServiceImpl;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.plu.services.jpa.PorderCapServiceImpl;
import ro.papetti.livrari.plu.services.jpa.PorderPozServiceImpl;
import ro.papetti.livrari.plu.services.jpa.SorderCapServiceImpl;
import ro.papetti.livrari.plu.services.jpa.SorderPozServiceImpl;


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
    private final SorderCapServiceImpl sCapPluService;
    private final SorderPozServiceImpl sLiniiPluService;
    private final PorderCapServiceImpl pCapPluService;
    private final PorderPozServiceImpl pLiniiPluService;

    private final ComandaHartaServiceImpl hartaService;


    
    public GeneralRestController(ComandaCapServiceImpl capSer,
                                 ComandaPozServiceImpl liniiSer,
                                 SorderCapServiceImpl sCapPluService,
                                 SorderPozServiceImpl sLiniiPluService,
                                 PorderCapServiceImpl pCapPluService,
                                 PorderPozServiceImpl pLiniiPluService,
                                 ComandaHartaServiceImpl hartaService){
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
    public ResponseEntity<ComandaHarta> getComadaCapById(@PathVariable int capId) {

        ComandaHarta comHarta = hartaService.getComandaHartaById(capId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc ComandaCap cu CapId: "+capId));
        return ResponseEntity.ok(comHarta);
    }

}
