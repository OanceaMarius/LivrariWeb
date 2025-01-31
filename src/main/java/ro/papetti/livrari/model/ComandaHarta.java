/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.util.List;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.pluriva.entity.Unitate;

/**
 *
 * @author MariusO
 */
public class ComandaHarta {
    private ComandaCap capLiv;
    private List<ComandaPoz> pozLiv; 
    private List<ComandaPluPoz>  pozPlu;
    private Unitate unitate;

    public ComandaHarta(ComandaCap capLiv) {
        this.capLiv = capLiv;
    }

    public ComandaHarta() {
    }

    public ComandaCap getCapLiv() {
        return capLiv;
    }

    public void setCapLiv(ComandaCap capLiv) {
        this.capLiv = capLiv;
    }

    public List<ComandaPoz> getPozLiv() {
        return pozLiv;
    }

    public void setPozLiv(List<ComandaPoz> pozLiv) {
        this.pozLiv = pozLiv;
    }

    public List<ComandaPluPoz> getPozPlu() {
        return pozPlu;
    }

    public void setPozPlu(List<ComandaPluPoz> pozPlu) {
        this.pozPlu = pozPlu;
    }

    public Unitate getUnitate() {
        return unitate;
    }

    public void setUnitate(Unitate unitate) {
        this.unitate = unitate;
    }
    
    

 
}
