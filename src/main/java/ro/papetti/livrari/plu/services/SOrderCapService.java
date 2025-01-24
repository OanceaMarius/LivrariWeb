/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import org.springframework.stereotype.Service;
import ro.papetti.PlurivaTabele.entity.SOrderCap;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.plu.repozitories.SOrderCapRepozitory;

/**
 *
 * @author MariusO
 */
@Service
public class SOrderCapService extends AbstractBaseService<SOrderCap, SOrderCapRepozitory> {
    
    public SOrderCapService(SOrderCapRepozitory rep) {
        super(rep);
    }
    
}
