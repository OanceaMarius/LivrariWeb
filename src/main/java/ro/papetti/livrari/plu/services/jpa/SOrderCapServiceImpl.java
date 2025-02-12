/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.plu.repozitories.SOrderCapRepozitory;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.pluriva.dto.SOrderCapDTOI;
import ro.papetti.pluriva.dto.SOrderPozDTOIFaraSOrderCap;
import ro.papetti.pluriva.entity.SOrderCap;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SOrderCapServiceImpl extends BaseServiceImpl<SOrderCap, SOrderCapRepozitory > implements SOrderCapService  {

    public SOrderCapServiceImpl(SOrderCapRepozitory repozitory) {
        super(repozitory);
    }

    /**
     * 
     * @param sOrderCapId
     * @param firmaId
     * @return Cantitatile livrate la fiecare pozitie de comanda in pluriva
     */
    @Override
    public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId){
        return rep.getCantitatiLivrate(sOrderCapId, firmaId);
    }
    
    @Override
    public  Optional<List<SOrderCap>>findByDataLivrare(Date dataLivrare){
        return rep.findByDataLivrare(dataLivrare);
    }
    
    public Optional<SOrderCap> findBySOrderCapId(int sOrderCapId){
        return rep.findById(sOrderCapId);
    }
    
    @Override
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId) {
            return rep.getCantitatiRezervate(sOrderCapId);
    }

    @Override
    public Optional<SOrderCapDTOI> findDTOBySOrderCapId(int sOrderCapId) {
        Optional<SOrderCapDTOI> sCap = rep.findDTOBySOrderCapId(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            if (!sCap.get().getPozitii().isEmpty()) {
                Hibernate.initialize(sCap.get().getPozitii().get(0).getpOrderPoz());
                
                for  (SOrderPozDTOIFaraSOrderCap sPoz: sCap.get().getPozitii()){
                    if (sPoz.getpOrderPoz()!=null) {
                        Hibernate.initialize(sPoz.getpOrderPoz().getpOrderCap());
                        Hibernate.initialize(sPoz.getpOrderPoz().getpOrderCap().getFurnizorUnitate());
                        break;
                    }
                }
            }
            
        }
        return sCap;
    }
   
}
