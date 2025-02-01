/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.plu.repozitories.FollowUpRepozitory;
import ro.papetti.pluriva.entity.FollowUp;

/**
 *
 * @author MariusO
 */
@Service
public class FollowUpService extends AbstractBaseService<FollowUp, FollowUpRepozitory> {
    
    public FollowUpService(FollowUpRepozitory repozitory) {
        super(repozitory);
    }
    
    
}
