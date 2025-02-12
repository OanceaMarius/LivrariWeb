/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.SOrderPozRepozitory;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.pluriva.dto.SOrderPozDTOI;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SOrderPozServiceImpl extends BaseServiceImpl<SOrderPoz, SOrderPozRepozitory> implements SOrderPozService {

    public SOrderPozServiceImpl(SOrderPozRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public List<SOrderPozDTOI> findPozitiiDTOBySOrderCapId(int sOrderCapId){
        return rep.findBySOrderCapSOrderCapId(sOrderCapId, SOrderPozDTOI.class);
    }
    
    @Override
    public List<SOrderPoz> findPozitiiBySOrderCapId(int sOrderCapId){
        return rep.findBySOrderCapSOrderCapId(sOrderCapId, SOrderPoz.class);
    }
  
}
