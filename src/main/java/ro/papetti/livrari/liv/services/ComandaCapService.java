/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services;

import org.springframework.stereotype.Service;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.liv.repozitories.ComandaCapRepozitory;


/**
 *
 * @author MariusO
 */
@Service
public class ComandaCapService extends AbstractBaseService<ComandaCap, ComandaCapRepozitory>{
         
    
    public ComandaCapService(ComandaCapRepozitory rep) {
        super(rep);
    }
    
    
    
}
