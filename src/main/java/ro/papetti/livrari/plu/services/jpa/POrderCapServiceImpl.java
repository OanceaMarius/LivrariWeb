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
public class POrderCapServiceImpl implements POrderCapService {

    public POrderCapServiceImpl(POrderCapRepozitory pOrderCapRepozitory) {
        this.pOrderCapRepozitory = pOrderCapRepozitory;
    }

    private final POrderCapRepozitory pOrderCapRepozitory;

    public Optional<POrderCap> findByPOrderCapId(int pOrderCapId) {
        return pOrderCapRepozitory.findById(pOrderCapId);

    }

    @Override
    public List<POrderPoz> findPOrderPozByPOrderCapId(int pOrderCapId) {

        POrderCap cap = pOrderCapRepozitory.findById(pOrderCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc POrderCap cu id: " + pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
        return cap.getPozitii();

    }

    @Override
    public Optional<POrderCap> findById(Integer pOrderCapId) {

        Optional<POrderCap> pCap = pOrderCapRepozitory.findById(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getPozitii());
        }
        return pCap;
    }

    @Override
    public List<POrderCap> findByDataLivrare(Date dataLivrare) {
        return pOrderCapRepozitory.findByDataLivrare(dataLivrare)
                .orElseThrow(() -> new EntityNotFoundException("NU gasesc POrderCap-uri cu data livrare mai mica decat: " + dataLivrare));
    }

    @Override
    public Optional<POrderCapDTOI> findDTOByPOrderCapId(Integer pOrderCapId) {
        Optional<POrderCapDTOI> pCap = pOrderCapRepozitory.findDTOByPOrderCapId(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getPozitii());
        }
        return pCap;
    }

}
