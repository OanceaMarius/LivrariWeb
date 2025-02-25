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
import ro.papetti.livrari.plu.repozitories.SorderCapRepozitory;
import ro.papetti.livrari.plu.services.SorderCapService;
import ro.papetti.pluriva.dtoi.SorderCapDTOI;
import ro.papetti.pluriva.dtoi.SorderPozDTOIFaraSorderCap;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.entity.SorderCap;
import ro.papetti.pluriva.entity.SorderPoz;
import ro.papetti.pluriva.entity.Unitate;


/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SorderCapServiceImpl extends BaseServiceImpl<SorderCap, SorderCapRepozitory> implements SorderCapService {

    public SorderCapServiceImpl(SorderCapRepozitory repozitory) {
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
    public Optional<List<SorderCap>> findByDataLivrare(Date dataLivrare) {
        return rep.findByDataLivrare(dataLivrare);
    }

 
    @Override
    public Optional<SorderCap> findById(int sOrderCapId) {
        Optional<SorderCap> sCap = rep.findById(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getUserIntroducere());
        }
        return sCap;
    }

    @Override
    public Optional<SorderCap> findByIdCuClient(int sorderCapId) {
        Optional<SorderCap> sCap = findById(sorderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(getClient(sCap.get()));
        }
        return sCap;
    }
    
    @Override
    public Optional<SorderCap> findByIdCuPozitii(int sOrderCapId) {
        Optional<SorderCap> sCap = findByIdCuClient(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            List<SorderPoz> listSpoz = sCap.get().getPozitii();
            if (listSpoz!=null) {
                for(SorderPoz sPoz:listSpoz){
                    Hibernate.initialize(sPoz.getProdus());
                }
            }
        }
        return sCap;
    }


    @Override
    public Optional<SorderCap> findByIdCuPozitiiSiLegaturaLaAprov(int sorderCapId) {
        Optional<SorderCap> sCap = findByIdCuPozitii(sorderCapId);
        if (sCap.isPresent()) {
            List<SorderPoz> listSPoz = sCap.get().getPozitii();
            for (SorderPoz sPoz : listSPoz) {
                Hibernate.initialize(sPoz.getPorderPoz());
                PorderPoz pPoz = sPoz.getPorderPoz();
                if (pPoz != null) {
                    /* TODO de facut cu dto */
//                    Hibernate.initialize(pPoz.getPorderCap());
//                    Hibernate.initialize(pPoz.getPorderCap().getFurnizorUnitate());
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
    public Optional<SorderCapDTOI> findDTOById(int sOrderCapId) {
        Optional<SorderCapDTOI> sCap = rep.findDTOIBySorderCapId(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getUserIntroducere());
        }
        return sCap;
    }

    @Override
    public Optional<SorderCapDTOI> findDTOByIdCuClient(int sOrderCapId) {
        Optional<SorderCapDTOI> sCap = findDTOById(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getClientUnitate());
            Hibernate.initialize(sCap.get().getClientLivrareUnitate());

        }
        return sCap;
    }

    @Override
    public Optional<SorderCapDTOI> findDTOByIdCuPozitii(int sOrderCapId) {
        Optional<SorderCapDTOI> sCap = findDTOByIdCuClient(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            List<SorderPozDTOIFaraSorderCap> listSpoz = sCap.get().getPozitii();
            for (SorderPozDTOIFaraSorderCap poz : listSpoz) {
                Hibernate.initialize(poz.getProdus());
            }
        }
        return sCap;
    }

    @Override
    public Optional<SorderCapDTOI> findDTOByIdCuPozitiiSiLegaturaLaAprov(int sOrderCapId) {
        Optional<SorderCapDTOI> sCap = findDTOByIdCuPozitii(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            List<SorderPozDTOIFaraSorderCap> listSpoz = sCap.get().getPozitii();
            for (SorderPozDTOIFaraSorderCap poz : listSpoz) {
                Hibernate.initialize(poz.getpOrderPoz());
                Hibernate.initialize(poz.getpOrderPoz().getpOrderCap());
                Hibernate.initialize(poz.getpOrderPoz().getpOrderCap().getFurnizorUnitate());
            }
        }
        return sCap;
    }

    /**
     *
     * @param sorderCap
     * @return Clientul cu livrare daca exista ori clientul de facturare
     */
    private Unitate getClient(SorderCap sorderCap){
        if (sorderCap.getClientLivrareUnitate()!=null)
            return sorderCap.getClientLivrareUnitate();

       return sorderCap.getClientUnitate();
    }

}
