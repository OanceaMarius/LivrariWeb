/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.papetti.LivrariTabele.entity.TblComandaAntetProgram;
import ro.papetti.livrari.repozitoriesLiv.ComandaAntetProgramRepozitory;


/**
 *
 * @author MariusO
 */
@Service
public class ComandaAntetProgramService {

//    public TblComandaAntetProgram getById(int i) {
//       
//    }
    
    @Autowired
    private  ComandaAntetProgramRepozitory rep;

    
    public TblComandaAntetProgram getById(int idComanda){
        return rep.findById(idComanda).get();
    }

}
