/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.plu.repozitories.UnitateRepozitory;
import ro.papetti.pluriva.entity.Unitate;

/**
 *
 * @author MariusO
 */
@Service
public class UnitateService extends AbstractBaseService<Unitate, UnitateRepozitory> {
    
    public UnitateService(UnitateRepozitory rep) {
        super(rep);
    }
    
    

}
