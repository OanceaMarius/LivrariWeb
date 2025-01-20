/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.services;

import org.springframework.stereotype.Service;
import ro.papetti.LivrariTabele.entity.TblComandaAntetProgram;
import ro.papetti.livrari.repozitoriesLiv.ComandaAntetProgramRepozitory;


/**
 *
 * @author MariusO
 */
@Service
public class ComandaAntetProgramService extends AbstractBaseService<TblComandaAntetProgram, Integer, ComandaAntetProgramRepozitory>{
    
    public ComandaAntetProgramService(ComandaAntetProgramRepozitory rep) {
        super(rep);
    }
    
}
