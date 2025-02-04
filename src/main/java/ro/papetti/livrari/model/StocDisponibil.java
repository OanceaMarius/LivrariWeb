/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.math.BigDecimal;

/**
 *
 * @author MariusO
 */
public class StocDisponibil {
    private BigDecimal stocDisponibil;
    private int produsId;

    public StocDisponibil( int produsId, BigDecimal stocDisponibil) {
        this.stocDisponibil = stocDisponibil;
        this.produsId = produsId;
    }

    public BigDecimal getStocDisponibil() {
        return stocDisponibil;
    }

    public void setStocDisponibil(BigDecimal stocDisponibil) {
        this.stocDisponibil = stocDisponibil;
    }

    public int getProdusId() {
        return produsId;
    }

    public void setProdusId(int produsId) {
        this.produsId = produsId;
    }
    
}
