/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.livrari.liv.repozitories.ComandaPozRepozitory;
import ro.papetti.livrari.liv.services.ComandaPozService;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("livrariTransactionManager")
public class ComandaPozServiceImpl implements ComandaPozService {

    public ComandaPozServiceImpl(ro.papetti.livrari.liv.repozitories.ComandaPozRepozitory comandaPozRepozitory) {
        this.comandaPozRepozitory = comandaPozRepozitory;
    }
    
    private final ComandaPozRepozitory comandaPozRepozitory;
    
    public List<ComandaPoz> findComenziPozByIdCap(int idProgram){
        return comandaPozRepozitory.findLiniiByIdProgram(idProgram);
    }
    


    @Override
    public List<ComandaPoz> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ComandaPoz> saveAll(Iterable<ComandaPoz> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ComandaPoz save(ComandaPoz entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<ComandaPoz> findById(Integer id) {
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
    public void delete(ComandaPoz entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
