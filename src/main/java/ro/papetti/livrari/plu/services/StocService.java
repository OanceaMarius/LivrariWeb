/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import java.util.Set;
import ro.papetti.livrari.model.StocDisponibil;

/**
 *
 * @author MariusO
 */
public interface StocService {

    public List<StocDisponibil> getStocDisponibilInGestiune(int FirmaId, int GestiuneId);

    public Set<StocDisponibil> getStocDisponibilInGestiuneOperationala(int FirmaId);
    
}
