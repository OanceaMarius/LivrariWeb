/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.PorderPozRepozitory;
import ro.papetti.livrari.plu.services.PorderPozService;
import ro.papetti.pluriva.entity.PorderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class PorderPozServiceImpl extends BaseServiceImpl<PorderPoz, PorderPozRepozitory> implements PorderPozService {

    public PorderPozServiceImpl(PorderPozRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public List<PorderPoz> findPozitiiByPOrderCapId(int pOrderCapId){
        return rep.findByPOrderCapId(pOrderCapId);
    }


    @Override
    public <T> List<T> findPozitiiDTOByPOrderCapId(int pOrderCapId, Class<T> type) {
        return rep.findDTOByPOrderCapId(pOrderCapId,type);
    }
}
