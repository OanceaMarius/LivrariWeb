/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.liv.repozitories.ComandaCapRepozitory;
import ro.papetti.livrari.liv.services.ComandaCapService;


/**
 *
 * @author MariusO
 */
@Service
@Transactional("livrariTransactionManager")
public class ComandaCapServiceImpl implements ComandaCapService{

    public ComandaCapServiceImpl(ro.papetti.livrari.liv.repozitories.ComandaCapRepozitory comandaCapRepozitory) {
        this.comandaCapRepozitory = comandaCapRepozitory;
    }
    private final ComandaCapRepozitory comandaCapRepozitory;

    @Override
    public List<ComandaCap> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ComandaCap> saveAll(Iterable<ComandaCap> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ComandaCap save(ComandaCap entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<ComandaCap> findById(Integer id) {
        return comandaCapRepozitory.findById(id);
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
    public void delete(ComandaCap entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}
