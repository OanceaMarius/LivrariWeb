/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.plu.repozitories.SOrderCapRepozitory;
import ro.papetti.pluriva.entity.SOrderCap;

/**
 *
 * @author MariusO
 */
@Service
public class SOrderCapService extends AbstractBaseService<SOrderCap, SOrderCapRepozitory> {
    
    public SOrderCapService(SOrderCapRepozitory rep) {
        super(rep);
    }
    
    public  Optional<List<SOrderCap>>findByDataLivrare(Date dataLivrare){
        return repository.findByDataLivrare(dataLivrare);
    }
    
}
