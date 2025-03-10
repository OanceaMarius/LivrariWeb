/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Transactional(value = "plurivaTransactionManager", readOnly = true)
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
    public List<StocDisponibil> getStocDisponibilInGestiuneOperationala(int FirmaId){
        int gestiuneOperationalaId = stocRepozitory.getGestiuneOperationalaPeFirma(FirmaId);
        return stocRepozitory.getStocDisponibilInGestiune(FirmaId,gestiuneOperationalaId);
    }

    @Override
    public int getGestiuneOperationalaPeFirma(int firmaId){
        return stocRepozitory.getGestiuneOperationalaPeFirma(firmaId);
    }


    @Override
    public Map<Integer, BigDecimal> getStocDisponibilInGestiuneFiltrat(int firmaId, int gestiuneId, List<Integer> produsIdList){

        List<StocDisponibil> stocDisponibilList = stocRepozitory.getStocDisponibilInGestiuneFiltrat(firmaId,gestiuneId,produsIdList);

        Map<Integer, BigDecimal> stocDisponibilMap = new HashMap<>();
        for (StocDisponibil sd: stocDisponibilList){
            stocDisponibilMap.put(sd.getProdusId(),sd.getStocDisponibil());
        }
        return  stocDisponibilMap;
    }


    @Override
    public Map<Integer, BigDecimal> getStocDisponibilInGestiuneOperationalaFiltrat(int firmaId, List<Integer> produsIdList){

        int gestiuneOperationalaId = getGestiuneOperationalaPeFirma(firmaId);
        List<StocDisponibil> stocDisponibilList = stocRepozitory.getStocDisponibilInGestiuneFiltrat(firmaId, gestiuneOperationalaId, produsIdList);

        Map<Integer, BigDecimal> stocDisponibilMap = new HashMap<>();
        for (StocDisponibil sd: stocDisponibilList){
            stocDisponibilMap.put(sd.getProdusId(),sd.getStocDisponibil());
        }
        return  stocDisponibilMap;
    }
}
