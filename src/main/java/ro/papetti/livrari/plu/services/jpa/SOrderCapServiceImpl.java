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
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.SOrderCap;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SOrderCapServiceImpl extends BaseServiceImpl<SOrderCap, SOrderCapRepozitory> implements SOrderCapService {

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
    public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId) {
        return rep.getCantitatiLivrate(sOrderCapId, firmaId);
    }

    @Override
    public Optional<List<SOrderCap>> findByDataLivrare(Date dataLivrare) {
        return rep.findByDataLivrare(dataLivrare);
    }

 
    @Override
    public Optional<SOrderCap> findById(int sOrderCapId) {
        Optional<SOrderCap> sCap = rep.findById(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getUserIntroducere());
        }
        return sCap;
    }

    @Override
    public Optional<SOrderCap> findByIdCuClient(int sOrderCapId) {
        Optional<SOrderCap> sCap = findById(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getClient());
        }
        return sCap;
    }
    
    @Override
    public Optional<SOrderCap> findByIdCuPozitii(int sOrderCapId) {
        Optional<SOrderCap> sCap = findByIdCuClient(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            List<SOrderPoz> listSpoz = sCap.get().getPozitii();
            if (listSpoz!=null) {
                for(SOrderPoz sPoz:listSpoz){
                    Hibernate.initialize(sPoz.getProdus());
                }
            }
        }
        return sCap;
    }


    @Override
    public Optional<SOrderCap> findByIdCuPozitiiSiLegaturaLaAprov(int sOrderCapId) {
        Optional<SOrderCap> sCap = findByIdCuPozitii(sOrderCapId);
        if (sCap.isPresent()) {
            List<SOrderPoz> listSPoz = sCap.get().getPozitii();
            for (SOrderPoz sPoz : listSPoz) {
                Hibernate.initialize(sPoz.getpOrderPoz());
                POrderPoz pPoz = sPoz.getpOrderPoz();
                if (pPoz != null) {
                    Hibernate.initialize(pPoz.getpOrderCap());
                    Hibernate.initialize(pPoz.getpOrderCap().getFurnizorUnitate());
                }
            }

        }
        return sCap;
    }

    @Override
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId) {
        return rep.getCantitatiRezervate(sOrderCapId);
    }

    @Override
    public Optional<SOrderCapDTOI> findDTOById(int sOrderCapId) {
        Optional<SOrderCapDTOI> sCap = rep.findDTOBySOrderCapId(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getUserIntroducere());
        }
        return sCap;
    }

    @Override
    public Optional<SOrderCapDTOI> findDTOByIdCuClient(int sOrderCapId) {
        Optional<SOrderCapDTOI> sCap = findDTOById(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getClientUnitate());
            Hibernate.initialize(sCap.get().getClientLivrareUnitate());

        }
        return sCap;
    }

    @Override
    public Optional<SOrderCapDTOI> findDTOByIdCuPozitii(int sOrderCapId) {
        Optional<SOrderCapDTOI> sCap = findDTOByIdCuClient(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            List<SOrderPozDTOIFaraSOrderCap> listSpoz = sCap.get().getPozitii();
            for (SOrderPozDTOIFaraSOrderCap poz : listSpoz) {
                Hibernate.initialize(poz.getProdus());
            }
        }
        return sCap;
    }

    @Override
    public Optional<SOrderCapDTOI> findDTOByIdCuPozitiiSiLegaturaLaAprov(int sOrderCapId) {
        Optional<SOrderCapDTOI> sCap = findDTOByIdCuPozitii(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            List<SOrderPozDTOIFaraSOrderCap> listSpoz = sCap.get().getPozitii();
            for (SOrderPozDTOIFaraSOrderCap poz : listSpoz) {
                Hibernate.initialize(poz.getpOrderPoz());
                Hibernate.initialize(poz.getpOrderPoz().getpOrderCap());
                Hibernate.initialize(poz.getpOrderPoz().getpOrderCap().getFurnizorUnitate());
            }
        }
        return sCap;
    }



}
