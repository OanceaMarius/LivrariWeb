/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.componente;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;

/**
 *
 * @author MariusO
 */
public interface InfoMarfa {

    Map<Integer, BigDecimal> getStocDisponibilInGestiuneFiltrat(int firmaId, int gestiuneId, List<Integer> produsIdList);

    Map<Integer, BigDecimal> getStocDisponibilInGestiuneOperationalaFiltrat(int firmaId, List<Integer> produsIdList);

    List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId);
    
    List<PozCantitate> getCantitatiRezervate(int sOrderCapId);

    List<StocDisponibil> getStocuriDisponibile(int firmaId);

    Map<Integer, BigDecimal> getCantitatiReceptionateByPorderCapId(int porderCapId);
}
