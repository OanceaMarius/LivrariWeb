/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ro.papetti.livrari.model.AbstractBaseService;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.repozitories.SOrderPozRepozitory;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
@Service
public class SOrderPozService extends AbstractBaseService<SOrderPoz, SOrderPozRepozitory> {
    
    public SOrderPozService(SOrderPozRepozitory rep) {
        super(rep);
    }
    
    public List<SOrderPoz> findPozitiiBySOrderCapId(int sOrderCapId){
        return repository.findPozitiiBySOrderCapId(sOrderCapId);
    }
    
    
    public List<StocDisponibil> getStocDisponibil( int firmaId, int gestiuneId){
        return repository.getStocDisponibilInGestiune(firmaId, gestiuneId);
    }
    /**
     * 
     * @param firmaId
     * @return Stocul disponibil in Gestiunea operationala definita pt firma trimisa
     */
    public List<StocDisponibil>  getStocDisponibilInGestiuneOperationala( int firmaId){
        return repository.getStocDisponibilInGestiuneOperationala(firmaId);
    }
    
    /**
     * 
     * @param sOrderCapId
     * @param firmaId
     * @return Cantitatile livrate la fiecare pozitie de comanda in pluriva
     */
    public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId){
        return repository.getCantitatiLivrate(sOrderCapId, firmaId);
    }
    
    /**
     * 
     * @param sOrderCapId
     * @return cantitatile ce au fost rezervate din stoc pt aceasta comanda 
     */
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId){
        return repository.getCantitatiRezervate(sOrderCapId);
    }
    
}
