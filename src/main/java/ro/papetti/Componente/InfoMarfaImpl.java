/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.Componente;

import java.util.List;
import org.springframework.stereotype.Component;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.services.SOrderPozService;

/**
 *
 * @author MariusO
 */
@Component
public class InfoMarfaImpl implements InfoMarfa {
    private final SOrderPozService serv;
    
    public InfoMarfaImpl(SOrderPozService service) {
        this.serv = service;
        System.out.println("InfoMarfa E ONLINE");
    }
    

            
            
    @Override
  public List<StocDisponibil>  getStocuriDisponibile(int firmaId){        
        return serv.getStocDisponibilInGestiuneOperationala(firmaId);
    }

    @Override
  public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId){
      return serv.getCantitatiLivrate(sOrderCapId, firmaId);
  }

    @Override
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId) {
        return serv.getCantitatiRezervate(sOrderCapId);
    }
    
}
