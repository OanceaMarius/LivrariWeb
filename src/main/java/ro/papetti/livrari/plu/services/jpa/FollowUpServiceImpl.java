/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.plu.repozitories.FollowUpRepozitory;
import ro.papetti.livrari.plu.services.FollowUpService;
import ro.papetti.pluriva.entity.FollowUp;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class FollowUpServiceImpl implements FollowUpService{

    public FollowUpServiceImpl(ro.papetti.livrari.plu.repozitories.FollowUpRepozitory followUpRep) {
        this.followUpRep = followUpRep;
    }

    private final FollowUpRepozitory followUpRep;
    
    @Override
    public List<FollowUp> findAll() {
        return followUpRep.findAll();
    }

    @Override
    public List saveAll(Iterable entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public Optional<FollowUp> findById(Integer id) {
        return followUpRep.findById(id);
    }

    @Override
    public long count() {
        return followUpRep.count();
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
    public FollowUp save(FollowUp entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(FollowUp entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
 
    
    
}
