/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.componente;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.livrari.plu.services.StocService;

/**
 *
 * @author MariusO
 */
@Component
public class InfoMarfaImpl implements InfoMarfa {

    private final StocService stocService;
    private final SOrderCapService sOrderCapService;

    public InfoMarfaImpl(StocService stocService, SOrderCapService sOrderCapService) {
        this.stocService = stocService;
        this.sOrderCapService = sOrderCapService;
        System.out.println("InfoMarfa E ONLINE");
    }

    @Override
    public Set<StocDisponibil> getStocuriDisponibile(int firmaId) {
        return stocService.getStocDisponibilInGestiuneOperationala(firmaId);
    }

    @Override
    public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId) {
        return sOrderCapService.getCantitatiLivrate(sOrderCapId, firmaId);
    }

    @Override
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId) {
        return sOrderCapService.getCantitatiRezervate(sOrderCapId);
    }

}
