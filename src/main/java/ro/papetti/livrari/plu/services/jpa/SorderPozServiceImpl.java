/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.SorderPozRepozitory;
import ro.papetti.livrari.plu.services.SorderPozService;
import ro.papetti.pluriva.dtoi.SorderPozDTOI;
import ro.papetti.pluriva.entity.SorderPoz;

import java.util.List;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SorderPozServiceImpl extends BaseServiceImpl<SorderPoz, SorderPozRepozitory> implements SorderPozService {

    public SorderPozServiceImpl(SorderPozRepozitory repozitory) {
        super(repozitory);
    }

    @Override
    public List<SorderPozDTOI> findPozitiiDTOBySOrderCapId(int sOrderCapId) {
        return rep.findBySOrderCapSOrderCapId(sOrderCapId, SorderPozDTOI.class);
    }

    @Override
    public List<SorderPozDTOI> findPozitiiDTOBySOrderCapIdCUProduse(int sOrderCapId) {
        List<SorderPozDTOI> listPoz = findPozitiiDTOBySOrderCapId(sOrderCapId);
        if (listPoz != null) {
            for (SorderPozDTOI poz : listPoz) {
                Hibernate.initialize(poz.getProdus());
            }
        }
        return listPoz;
    }


    public List<SorderPozDTOI> findPozitiiDTOBySOrderCapIdCuProduseSiFurnizori(int sOrderCapId) {
        List<SorderPozDTOI> listPoz = findPozitiiDTOBySOrderCapIdCUProduse(sOrderCapId);
        if (listPoz != null) {
            for (SorderPozDTOI poz : listPoz) {
                Hibernate.initialize(poz.getPorderPoz());
                var pPoz = poz.getPorderPoz();
                if (pPoz != null) {
                    Hibernate.initialize(poz.getPorderPoz().getpOrderCap());
                    Hibernate.initialize(poz.getPorderPoz().getpOrderCap().getFurnizorUnitate());
                }

            }
        }
        return listPoz;
    }

    @Override
    public List<SorderPoz> findPozitiiBySOrderCapId(int sOrderCapId) {
        return rep.findBySOrderCapSOrderCapId(sOrderCapId, SorderPoz.class);
    }

}
