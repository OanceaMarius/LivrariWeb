/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import org.springframework.stereotype.Service;
import ro.papetti.PlurivaTabele.entity.TblTipLivrare;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.plu.repozitories.TblTipLivrareRepozitory;

/**
 *
 * @author MariusO
 */
@Service
public class TblTipLivrareService extends AbstractBaseService<TblTipLivrare, TblTipLivrareRepozitory> {
    
    public TblTipLivrareService(TblTipLivrareRepozitory rep) {
        super(rep);
    }
    
}
