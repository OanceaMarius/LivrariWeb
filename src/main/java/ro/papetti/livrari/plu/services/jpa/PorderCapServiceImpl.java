/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.PorderCapRepozitory;
import ro.papetti.livrari.plu.services.PorderCapService;
import ro.papetti.pluriva.dtoi.PorderCapDTOI;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.PorderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class PorderCapServiceImpl extends BaseServiceImpl<PorderCap, PorderCapRepozitory> implements PorderCapService {



    public PorderCapServiceImpl(PorderCapRepozitory repozitory) {
        super(repozitory);
    }

    public Optional<PorderCap> findByPOrderCapId(int pOrderCapId) {
        return rep.findById(pOrderCapId);

    }

    @Override
    public List<PorderPoz> findPOrderPozByPOrderCapId(int pOrderCapId) {

        PorderCap cap = rep.findById(pOrderCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc POrderCap cu id: " + pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
        return cap.getPozitii();

    }
/**
 * Aduce doar Capul fara pozitii
 * @param pOrderCapId
 * @return 
 */
    @Override
    public Optional<PorderCap> findById(Integer pOrderCapId) {

        Optional<PorderCap> pCap = rep.findById(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getUserIntroducere());
        }
        return pCap;
    }

    /**
     * Aduce si datele despre partener
     * @param pOrderCapId
     * @return
     */
    public Optional<PorderCap> findByIdCuFurnizor(Integer pOrderCapId) {
        Optional<PorderCap> pCap = findById(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getFurnizorUnitate());
        }
        return pCap;
    }

    /**
     * Aduce si datele departener si de pozitii
     * @param pOrderCapId
     * @return
     */
    @Override
    public Optional<PorderCap> findByIdCuPozitii(Integer pOrderCapId) {

        Optional<PorderCap> pCap = findByIdCuFurnizor(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getPozitii());
            List<PorderPoz> listPpoz =pCap.get().getPozitii();
            if (listPpoz!=null) {
                for(PorderPoz pPoz:listPpoz){
                    Hibernate.initialize(pPoz.getProdus());
                }
            }
        }
        return pCap;
    }

    /**
     * Aduce si datele despre partener si pozitii dar si cele de comenzile de client legate
     * @param pOrderCapId
     * @return
     */
    @Override
        public Optional<PorderCap> findByIdCuPozitiiSiLegaturaLaComenzi(Integer pOrderCapId) {

        Optional<PorderCap> pCap = findByIdCuPozitii(pOrderCapId);
        if (pCap.isPresent()) {
           
            List<PorderPoz> listPPoz = pCap.get().getPozitii();
            if (listPPoz!=null) {
                for(PorderPoz pPoz: listPPoz){
                    Hibernate.initialize(pPoz.getSorderPoz());
                    Hibernate.initialize(pPoz.getSorderPoz().getSorderCap());
                    Hibernate.initialize(pPoz.getSorderPoz().getSorderCap().getUserIntroducere());
                    Hibernate.initialize(pPoz.getSorderPoz().getSorderCap().getClientUnitate());
                    Hibernate.initialize(pPoz.getSorderPoz().getSorderCap().getClientLivrareUnitate());
                }
            }
            
        }
        return pCap;
    }

    @Override
    public List<PorderCap> findByDataLivrare(Date dataLivrare) {
        List<PorderCap> listPcap = rep.findByDataLivrare(dataLivrare);
        if (!listPcap.isEmpty()){
            for (PorderCap pOrderCap:listPcap){
                Hibernate.initialize(pOrderCap.getFurnizorUnitate());
                Hibernate.initialize(pOrderCap.getPozitii());
                Hibernate.initialize(pOrderCap.getUserIntroducere());
                Hibernate.initialize(pOrderCap.getFurnizorUnitate().getUserIntroducere());
                Hibernate.initialize(pOrderCap.getFurnizorUnitate().getUserModificare());
            }
        }
        return listPcap;
    }

    @Override
    public Optional<PorderCapDTOI> findDTOByPOrderCapId(Integer pOrderCapId) {
        Optional<PorderCapDTOI> pCap = rep.findDTOByPOrderCapId(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getPozitii());
            Hibernate.initialize(pCap.get().getFurnizorUnitate());
        }
        return pCap;
    }

}
