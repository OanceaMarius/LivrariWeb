/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.liv.repozitories.ComandaPozRepozitory;

/**
 *
 * @author MariusO
 */
@Service
public class ComandaPozService extends AbstractBaseService<ComandaPoz, ComandaPozRepozitory> {
    
    public ComandaPozService(ComandaPozRepozitory rep) {
        super(rep);
    }
    
    public List<ComandaPoz> findComenziPozByIdCap(int idProgram){
        return repository.findLiniiByIdProgram(idProgram);
    }
    
    
//    @Transactional
//    public List<String> getTestProc(int nr){
//        return repository.getTest(nr);
//    }
    
}
