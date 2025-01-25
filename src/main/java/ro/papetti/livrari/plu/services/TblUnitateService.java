/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import org.springframework.stereotype.Service;
import ro.papetti.PlurivaTabele.entity.TblUnitate;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.model.Unitate;
import ro.papetti.livrari.plu.repozitories.TblUnitateRepozitory;

/**
 *
 * @author MariusO
 */
@Service
public class TblUnitateService extends AbstractBaseService<TblUnitate, TblUnitateRepozitory> {
    
    public TblUnitateService(TblUnitateRepozitory rep) {
        super(rep);
    }
    
    
    public Unitate findUnitateWrwpperById(int unitateId){
        return repository.findUnitateWrapperById(unitateId);
    }
}
