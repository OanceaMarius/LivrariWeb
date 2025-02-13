/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.componente;

import java.util.List;
import java.util.Set;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;

/**
 *
 * @author MariusO
 */
public interface InfoMarfa {

    List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId);
    
    List<PozCantitate> getCantitatiRezervate(int sOrderCapId);

    Set<StocDisponibil> getStocuriDisponibile(int firmaId);
    
}
