/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.POrderPozRepozitory;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.pluriva.dto.POrderPozDTOI;
import ro.papetti.pluriva.entity.POrderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class POrderPozServiceImpl extends BaseServiceImpl<POrderPoz, POrderPozRepozitory> implements POrderPozService{

    public POrderPozServiceImpl(POrderPozRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public List<POrderPoz> findPozitiiByPOrderCapId(int pOrderCapId){
        return rep.findByPOrderCapId(pOrderCapId);
    }


    @Override
    public <T> List<T> findPozitiiDTOByPOrderCapId(int pOrderCapId, Class<T> type) {
        return rep.findDTOByPOrderCapId(pOrderCapId,type);
    }
}
