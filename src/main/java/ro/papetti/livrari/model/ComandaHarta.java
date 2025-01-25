/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.util.List;
import lombok.Data;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.ComandaPoz;

/**
 *
 * @author MariusO
 */

public @Data class ComandaHarta {
    private ComandaCap capLiv;
    private List<ComandaPoz> pozLiv; 
    private List<ComandaPluPoz>  pozPlu;
    private Unitate unitate;

    public ComandaHarta(ComandaCap capLiv) {
        this.capLiv = capLiv;
    }

    public ComandaHarta() {
    }
    
    

 
}
