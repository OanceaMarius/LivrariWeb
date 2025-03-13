/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.pluriva.entity.FurnizorProdus;

/**
 *
 * @author MariusO
 */
public interface StocService {

    public List<StocDisponibil> getStocDisponibilInGestiune(int FirmaId, int GestiuneId);

    public List<StocDisponibil> getStocDisponibilInGestiuneOperationala(int FirmaId);

    int getGestiuneOperationalaPeFirma(int firmaId);

    Map<Integer, BigDecimal> getStocDisponibilInGestiuneFiltrat(int firmaId, int gestiuneId, List<Integer> produsIdList);

    Map<Integer, BigDecimal> getStocDisponibilInGestiuneOperationalaFiltrat(int firmaId, List<Integer> produsIdList);


    Map<Integer, FurnizorProdus> getProduseLaFurnizor(int divizieId, int firmaId, int furnizorId, List<Integer> produsIdList);

    Map<Integer, FurnizorProdus> getProduseLaFurnizorPeComanda(int porderCapId);
}
