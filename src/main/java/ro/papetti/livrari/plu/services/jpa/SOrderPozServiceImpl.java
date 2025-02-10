/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.plu.repozitories.SOrderPozRepozitory;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.pluriva.dto.SOrderPozDTO;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SOrderPozServiceImpl implements SOrderPozService {

    public SOrderPozServiceImpl(ro.papetti.livrari.plu.repozitories.SOrderPozRepozitory sOrderPozRepozitory) {
        this.sOrderPozRepozitory = sOrderPozRepozitory;
    }
    
    private final SOrderPozRepozitory sOrderPozRepozitory;
    

    
    @Override
    public List<SOrderPozDTO> findPozitiiDTOBySOrderCapId(int sOrderCapId){
        return sOrderPozRepozitory.findBySOrderCapSOrderCapId(sOrderCapId, SOrderPozDTO.class);
    }
    
    @Override
    public List<SOrderPoz> findPozitiiBySOrderCapId(int sOrderCapId){
        return sOrderPozRepozitory.findBySOrderCapSOrderCapId(sOrderCapId, SOrderPoz.class);
    }
    
    
   



    @Override
    public List<SOrderPoz> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SOrderPoz> saveAll(Iterable<SOrderPoz> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SOrderPoz save(SOrderPoz entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<SOrderPoz> findById(Integer id) {
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
    public void delete(SOrderPoz entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
