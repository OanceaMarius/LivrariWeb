/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controllers.rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.PlurivaTabele.entity.POrderCap;
import ro.papetti.PlurivaTabele.entity.POrderPoz;
import ro.papetti.PlurivaTabele.entity.SOrderCap;
import ro.papetti.PlurivaTabele.entity.SOrderPoz;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.liv.services.ComandaPozService;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.model.ComandaPluPoz;
import ro.papetti.livrari.model.Unitate;
import ro.papetti.livrari.plu.services.POrderCapService;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.livrari.plu.services.TblUnitateService;
import ro.papetti.livrari.utilitare.Transformers;


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
    private final TblUnitateService tblUnitateService;

    public GeneralRestController(
            ComandaCapService capSer, 
            ComandaPozService liniiProSer, 
            SOrderCapService sCapPluService, 
            SOrderPozService sLiniiPluService, 
            POrderCapService pCapPluService, 
            POrderPozService pLiniiPluService,
            TblUnitateService tblUnitateService
            ) {
        this.capSer = capSer;
        this.liniiSer = liniiProSer;
        this.sCapPluService=sCapPluService;
        this.sLiniiPluService=sLiniiPluService;
        this.pCapPluService=pCapPluService;
        this.pLiniiPluService=pLiniiPluService;
        this.tblUnitateService=tblUnitateService;
    }
    
    @GetMapping("/ComandaHarta/{capId}")
    public ComandaHarta getComadaCapById(@PathVariable int capId){
        

        ComandaCap comCap = capSer.findById(capId).get();
        ComandaHarta comanda;
        if (comCap==null){
            comanda= new ComandaHarta();
            return comanda;
        }else{
            comanda = new ComandaHarta(comCap);
        }
        
        
//        ComandaHarta comanda = new ComandaHarta(comCap);

        int orderCapId =comCap.getOrderCapId();
        List<ComandaPoz> listComPoz = liniiSer.findComenziPozByIdCap(capId);
        int com = comCap.getCom();
        int unitateId=0 ;
//        int orderCapId = 0;
        List listPluPoz = new ArrayList<ComandaPluPoz>();
        Unitate unitate ;
        //aduc lista cu liniile de la comanda specifica
        if (com == 0) { //furnizori
            List<POrderPoz> listPoz = pLiniiPluService.findPozitiiByPOrderCapId(orderCapId);
            listPluPoz = Transformers.getComandaPluPozFromP(listPoz);
            POrderCap orderCap = pCapPluService.findById(orderCapId).orElse(null);
            if(orderCap != null)
                unitateId = orderCap.getFurnizorId();
        }else if(com == 1){//clienti
            List<SOrderPoz> listPoz = sLiniiPluService.findPozitiiBySOrderCapId(orderCapId);
            listPluPoz = Transformers.getComandaPluPozFromS(listPoz);
            SOrderCap orderCap = sCapPluService.findById(orderCapId).get();
            unitateId = orderCap.getClientID();
        }else if(com == 2){//Activitati programate
            //TODO implementare
            
         }else if(com == 1){//Obiective fixe
               //TODO implementare
         }
         unitate = tblUnitateService.findUnitateWrwpperById(unitateId);
        
//        ComandaHarta comanda = new ComandaHarta(comCap);
        comanda.setPozLiv(listComPoz);
        comanda.setPozPlu(listPluPoz);
        comanda.setUnitate(unitate);
        
        
        return comanda;
    }
    
      
//    @GetMapping("/liniiProgram/proc/{nr}")
//    public List<String> getProcedure(@PathVariable int nr){
//        return liniiProSer.getTestProc(nr);
//    }
}
