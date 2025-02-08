/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.repozitories.StocRepozitory;
import ro.papetti.livrari.plu.services.StocService;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class StocServiceImpl implements StocService{

    public StocServiceImpl(StocRepozitory stocRepozitory) {
        this.stocRepozitory = stocRepozitory;
    }
    private final StocRepozitory stocRepozitory;
    
    @Override
    public List<StocDisponibil> getStocDisponibilInGestiune(int FirmaId, int GestiuneId){
        return stocRepozitory.getStocDisponibilInGestiune(FirmaId, GestiuneId);
    }
    
    
    @Override
    public Set<StocDisponibil>  getStocDisponibilInGestiuneOperationala(int FirmaId){
        return stocRepozitory.getStocDisponibilInGestiuneOperationala(FirmaId);
    }
}
