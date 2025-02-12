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
import ro.papetti.livrari.plu.repozitories.POrderCapRepozitory;
import ro.papetti.livrari.plu.services.POrderCapService;
import ro.papetti.pluriva.dto.POrderCapDTOI;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class POrderCapServiceImpl extends BaseServiceImpl<POrderCap, POrderCapRepozitory> implements POrderCapService {



    public POrderCapServiceImpl(POrderCapRepozitory repozitory) {
        super(repozitory);
    }

    public Optional<POrderCap> findByPOrderCapId(int pOrderCapId) {
        return rep.findById(pOrderCapId);

    }

    @Override
    public List<POrderPoz> findPOrderPozByPOrderCapId(int pOrderCapId) {

        POrderCap cap = rep.findById(pOrderCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc POrderCap cu id: " + pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
        return cap.getPozitii();

    }

    @Override
    public Optional<POrderCap> findById(Integer pOrderCapId) {

        Optional<POrderCap> pCap = rep.findById(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getPozitii());
        }
        return pCap;
    }

    @Override
    public List<POrderCap> findByDataLivrare(Date dataLivrare) {
        return rep.findByDataLivrare(dataLivrare)
                .orElseThrow(() -> new EntityNotFoundException("NU gasesc POrderCap-uri cu data livrare mai mica decat: " + dataLivrare));
    }

    @Override
    public Optional<POrderCapDTOI> findDTOByPOrderCapId(Integer pOrderCapId) {
        Optional<POrderCapDTOI> pCap = rep.findDTOByPOrderCapId(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getPozitii());
        }
        return pCap;
    }

}
