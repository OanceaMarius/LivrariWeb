/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ro.papetti.LivrariTabele.entity.TblComandaLiniiProgram;
import ro.papetti.livrari.repozitoriesLiv.ComandaLiniiProgramRepozitory;

/**
 *
 * @author MariusO
 */
@Service
public class ComandaLiniiProgramService extends AbstractBaseService<TblComandaLiniiProgram, ComandaLiniiProgramRepozitory> {
    
    public ComandaLiniiProgramService(ComandaLiniiProgramRepozitory rep) {
        super(rep);
    }
    
    public List<TblComandaLiniiProgram> findLiniiByIdProgram(int idProgram){
        return repository.findLiniiByIdProgram(idProgram);
    }
    
}
