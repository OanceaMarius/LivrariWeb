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
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.plu.repozitories.SOrderCapRepozitory;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.pluriva.dto.SOrderCapDTOI;
import ro.papetti.pluriva.dto.SOrderPozDTOIFaraSOrderCap;
import ro.papetti.pluriva.entity.SOrderCap;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SOrderCapServiceImpl implements SOrderCapService  {

    public SOrderCapServiceImpl(SOrderCapRepozitory orderCapRepozitory) {
        this.sOrderCapRepozitory = orderCapRepozitory;
    }
    private final SOrderCapRepozitory sOrderCapRepozitory;
    
    /**
     * 
     * @param sOrderCapId
     * @param firmaId
     * @return Cantitatile livrate la fiecare pozitie de comanda in pluriva
     */
    @Override
    public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId){
        return sOrderCapRepozitory.getCantitatiLivrate(sOrderCapId, firmaId);
    }
    
    @Override
    public  Optional<List<SOrderCap>>findByDataLivrare(Date dataLivrare){
        return sOrderCapRepozitory.findByDataLivrare(dataLivrare);
    }
    
    public Optional<SOrderCap> findBySOrderCapId(int sOrderCapId){
        return sOrderCapRepozitory.findById(sOrderCapId);
    }
    
    @Override
    public Optional<SOrderCap> findById(Integer sOrderCapId){
        return sOrderCapRepozitory.findById(sOrderCapId);
    }




    @Override
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId) {
            return sOrderCapRepozitory.getCantitatiRezervate(sOrderCapId);
    }

    @Override
    public Optional<SOrderCapDTOI> findDTOBySOrderCapId(int sOrderCapId) {
        Optional<SOrderCapDTOI> sCap = sOrderCapRepozitory.findDTOBySOrderCapId(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            if (!sCap.get().getPozitii().isEmpty()) {
                Hibernate.initialize(sCap.get().getPozitii().get(0).getpOrderPoz());
                
                for  (SOrderPozDTOIFaraSOrderCap sPoz: sCap.get().getPozitii()){
                    if (sPoz.getpOrderPoz()!=null) {
                        Hibernate.initialize(sPoz.getpOrderPoz().getpOrderCap());
                        Hibernate.initialize(sPoz.getpOrderPoz().getpOrderCap().getFurnizorUnitate());
                        break;
                    }
                }
            }
            
        }
        return sCap;
    }

    @Override
    public List<SOrderCap> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SOrderCap> saveAll(Iterable<SOrderCap> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SOrderCap save(SOrderCap entity) {
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
    public void delete(SOrderCap entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
  
    
}
