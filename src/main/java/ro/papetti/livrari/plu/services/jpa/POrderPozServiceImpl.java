/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.plu.repozitories.POrderPozRepozitory;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.pluriva.dto.POrderPozDTOI;
import ro.papetti.pluriva.entity.POrderPoz;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class POrderPozServiceImpl implements POrderPozService{

    public POrderPozServiceImpl(ro.papetti.livrari.plu.repozitories.POrderPozRepozitory pOrderPozRepozitory) {
        this.pOrderPozRepozitory = pOrderPozRepozitory;
    }
    
    private final  POrderPozRepozitory pOrderPozRepozitory;
    
    @Override
    public List<POrderPoz> findPozitiiByPOrderCapId(int pOrderCapId){
        return pOrderPozRepozitory.findByPOrderCapId(pOrderCapId).orElse(new ArrayList<POrderPoz>());
    }


    @Override
    public POrderPoz save(POrderPoz entity) {
        return pOrderPozRepozitory.save(entity);
    }

    @Override
    public List<POrderPoz> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<POrderPoz> saveAll(Iterable<POrderPoz> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<POrderPoz> findById(Integer id) {
        return pOrderPozRepozitory.findById(id);
    }

    @Override
    public long count() {
           return pOrderPozRepozitory.count();
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(POrderPoz entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<POrderPozDTOI> findPozitiiDTOByPOrderCapId(int pOrderCapId) {
        return pOrderPozRepozitory.findByPOrderCapPOrderCapId(pOrderCapId, POrderPozDTOI.class);
    }


}
