/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.plu.services.POrderCapService;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;

/**
 *
 * @author MariusO
 */
@Service
public class ComandaHartaService {
    
//    @Autowired
    
    private final ComandaCapService capService;
    private final ComandaPozService pozService;
    private final POrderCapService pOrderCapService;
    private final POrderPozService pOrderPozService;
    


    public ComandaHartaService(ComandaCapService capService, ComandaPozService pozService, POrderCapService pOrderCapService, POrderPozService pOrderPozService) {
        this.capService = capService;
        this.pozService = pozService;
        this.pOrderCapService = pOrderCapService;
        this.pOrderPozService = pOrderPozService;
    }
    
    public ComandaHarta getComandaHartaById(int capId){
//        ComandaHarta comandaHarta ;//= new ComandaHarta();
        
        ComandaCap comandaCap = capService.findById(capId).get();
        ComandaHarta comandaHarta = new ComandaHarta(comandaCap);
        POrderCap pOrderCap = pOrderCapService.findByPOrderCapId(capId).orElse(new POrderCap());
        comandaHarta.setUnitate(pOrderCap.getFurnizorUnitate());
        List<POrderPoz> pozitiiCom = pOrderPozService.findPozitiiByPOrderCapId(pOrderCap.getPOrderCapId());

        comandaHarta.setPozPluFromPOrder(pozitiiCom);
        return comandaHarta;
    }
    
}
