/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.componente;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.services.SorderCapService;
import ro.papetti.livrari.plu.services.StocService;

/**
 *
 * @author MariusO
 */
@Component
public class InfoMarfaImpl implements InfoMarfa {

    private final StocService stocService;
    private final SorderCapService sorderCapService;

    public InfoMarfaImpl(StocService stocService, SorderCapService sOrderCapService) {
        this.stocService = stocService;
        this.sorderCapService = sOrderCapService;
//        System.out.println("InfoMarfa E ONLINE");
    }

    @Override
    public List<StocDisponibil> getStocuriDisponibile(int firmaId) {
        return stocService.getStocDisponibilInGestiuneOperationala(firmaId);
    }

    @Override
    public Map<Integer, BigDecimal> getStocDisponibilInGestiuneFiltrat(int firmaId, int gestiuneId, List<Integer> produsIdList){
        return stocService.getStocDisponibilInGestiuneFiltrat(firmaId, gestiuneId, produsIdList);
    }

    @Override
    public Map<Integer, BigDecimal> getStocDisponibilInGestiuneOperationalaFiltrat(int firmaId, List<Integer> produsIdList){
        int gestiuneOperationalaId = stocService.getGestiuneOperationalaPeFirma(firmaId);
        return stocService.getStocDisponibilInGestiuneFiltrat(firmaId, gestiuneOperationalaId, produsIdList);
    }

    @Override
    public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId) {
        return sorderCapService.getCantitatiLivrate(sOrderCapId, firmaId);
    }

    @Override
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId) {
        return sorderCapService.getCantitatiRezervate(sOrderCapId);
    }

}
