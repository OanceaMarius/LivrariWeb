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
public class PozCantitate {
    private int pozId;
    private BigDecimal cantitate;

    public PozCantitate(int pozId, BigDecimal cantitate) {
        this.pozId = pozId;
        this.cantitate = cantitate;
    }

    public int getPozId() {
        return pozId;
    }

    public void setPozId(int pozId) {
        this.pozId = pozId;
    }

    public BigDecimal getCantitate() {
        return cantitate;
    }

    public void setCantitate(BigDecimal cantitate) {
        this.cantitate = cantitate;
    }

   
    
}
