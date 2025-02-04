/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.Componente;

import java.util.List;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;

/**
 *
 * @author MariusO
 */
public interface InfoMarfa {

    List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId);

    List<StocDisponibil> getStocuriDisponibile(int firmaId);
    
}
