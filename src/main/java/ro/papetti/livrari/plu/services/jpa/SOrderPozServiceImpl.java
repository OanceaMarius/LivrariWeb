/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.List;
import org.hibernate.Hibernate;
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
    public List<SOrderPozDTOI> findPozitiiDTOBySOrderCapId(int sOrderCapId) {
        return rep.findBySOrderCapSOrderCapId(sOrderCapId, SOrderPozDTOI.class);
    }

    @Override
    public List<SOrderPozDTOI> findPozitiiDTOBySOrderCapIdCUProduse(int sOrderCapId) {
        List<SOrderPozDTOI> listPoz = findPozitiiDTOBySOrderCapId(sOrderCapId);
        if (listPoz != null) {
            for (SOrderPozDTOI poz : listPoz) {
                Hibernate.initialize(poz.getProdus());
            }
        }
        return listPoz;
    }


    public List<SOrderPozDTOI> findPozitiiDTOBySOrderCapIdCuProduseSiFurnizori(int sOrderCapId) {
        List<SOrderPozDTOI> listPoz = findPozitiiDTOBySOrderCapIdCUProduse(sOrderCapId);
        if (listPoz != null) {
            for (SOrderPozDTOI poz : listPoz) {
                Hibernate.initialize(poz.getpOrderPoz());
                var pPoz = poz.getpOrderPoz();
                if (pPoz != null) {
                    Hibernate.initialize(poz.getpOrderPoz().getpOrderCap());
                    Hibernate.initialize(poz.getpOrderPoz().getpOrderCap().getFurnizorUnitate());
                }

            }
        }
        return listPoz;
    }

    @Override
    public List<SOrderPoz> findPozitiiBySOrderCapId(int sOrderCapId) {
        return rep.findBySOrderCapSOrderCapId(sOrderCapId, SOrderPoz.class);
    }

}
