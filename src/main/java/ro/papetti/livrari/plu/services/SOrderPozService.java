/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.plu.repozitories.SOrderPozRepozitory;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
@Service
public class SOrderPozService extends AbstractBaseService<SOrderPoz, SOrderPozRepozitory> {
    
    public SOrderPozService(SOrderPozRepozitory rep) {
        super(rep);
    }
    
    public List<SOrderPoz> findPozitiiBySOrderCapId(int sOrderCapId){
        return repository.findPozitiiBySOrderCapId(sOrderCapId);
    }
}
