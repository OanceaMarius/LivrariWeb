/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.model.TipCom;
import ro.papetti.livrari.plu.services.POrderCapService;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.SOrderCap;
import ro.papetti.pluriva.entity.SOrderPoz;

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
    private final SOrderCapService sOrderCapService;
    private final SOrderPozService sOrderPozService;


    public ComandaHartaService(ComandaCapService capService, ComandaPozService pozService, 
            POrderCapService pOrderCapService, POrderPozService pOrderPozService, 
            SOrderCapService sOrderCapService, SOrderPozService sOrderPozService) {
        this.capService = capService;
        this.pozService = pozService;
        this.pOrderCapService = pOrderCapService;
        this.pOrderPozService = pOrderPozService;
        this.sOrderCapService = sOrderCapService;
        this.sOrderPozService = sOrderPozService;
    }
    
    public ComandaHarta getComandaHartaById(int capId){
        
        ComandaCap comandaCap = capService.findById(capId).get();
        ComandaHarta comandaHarta = new ComandaHarta(comandaCap);

        if (comandaCap.getCom().equals(TipCom.FURNIZOR.name())) {
            POrderCap pOrderCap = pOrderCapService
                    .findByPOrderCapId(comandaCap.getOrderCapId())
                    .orElse(new POrderCap());
            comandaHarta.setUnitate(pOrderCap.getFurnizorUnitate());
            List<POrderPoz> pozitiiCom = pOrderPozService.findPozitiiByPOrderCapId(pOrderCap.getPOrderCapId());
            comandaHarta.setPozPluFromPOrder(pozitiiCom);
            return comandaHarta;
        }if(comandaCap.getCom().equals(TipCom.CLIENT.name())){
            SOrderCap sOrderCap = sOrderCapService
                    .findBySOrderCapId(comandaCap.getOrderCapId())
                    .orElse(new SOrderCap());
            comandaHarta.setUnitate(sOrderCap.getClient());
            List<SOrderPoz> pozitiiCom = sOrderPozService.
                    findPozitiiBySOrderCapId(sOrderCap.getSOrderCapId());
            comandaHarta.setPozPluFromSOrder(pozitiiCom);
            return comandaHarta;
            
        }if(comandaCap.getCom().equals(TipCom.ACT_PRO.name())){
            
            return comandaHarta;
        }if(comandaCap.getCom().equals(TipCom.OBI_FIX.name())){
            
            return comandaHarta;
        }else{
            throw new RuntimeException("Nu e acceptat inca tipul de comanda: " + comandaCap.getCom());
        }
        
        
        
    }
    
}
