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
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class POrderCapServiceImpl implements POrderCapService  {

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
                .orElseThrow(()-> new EntityNotFoundException("Nu gasesc POrderCap cu id: "+ pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
        return cap.getPozitii();
        
    }

    @Override
    public Optional<POrderCap> findById(Integer pOrderCapId) {
        return pOrderCapRepozitory.findById(pOrderCapId);
    }

    @Override
    public List<POrderCap> findByDataLivrare(Date dataLivrare) {
       return pOrderCapRepozitory.findByDataLivrare(dataLivrare)
               .orElseThrow(()-> new EntityNotFoundException("NU gasesc POrderCap-uri cu data livrare mai mica decat: "+dataLivrare));
    }

    @Override
    public List<POrderCap> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<POrderCap> saveAll(Iterable entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public POrderCap save(POrderCap entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(POrderCap entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}
