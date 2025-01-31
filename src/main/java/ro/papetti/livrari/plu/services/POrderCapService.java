/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.plu.repozitories.POrderCapRepozitory;
import ro.papetti.pluriva.entity.POrderCap;

/**
 *
 * @author MariusO
 */
@Service
public class POrderCapService extends AbstractBaseService<POrderCap, POrderCapRepozitory> {
    
    public POrderCapService(POrderCapRepozitory rep) {
        super(rep);
    }
    
    public  List<POrderCap>findByDataLivrare(Date dataLivrare){
        return repository.findByDataLivrare(dataLivrare);
    }
    
    public POrderCap findByPOrderCapId(int pOrderCapId){
        return repository.findById(pOrderCapId).get();
    }
    
}
