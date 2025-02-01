/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.plu.repozitories.POrderPozRepozitory;
import ro.papetti.pluriva.entity.POrderPoz;

/**
 *
 * @author MariusO
 */
@Service
public class POrderPozService extends AbstractBaseService<POrderPoz, POrderPozRepozitory> {
    
    public POrderPozService(POrderPozRepozitory rep) {
        super(rep);
    }
    
    public List<POrderPoz> findPozitiiByPOrderCapId(int pOrderCapId){
        return repository.findByPOrderCapId(pOrderCapId).orElse(new ArrayList<POrderPoz>());
    }
}
