/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import lombok.*;

import java.math.BigDecimal;

/**
 *
 * @author MariusO
 */
@Data
public class StocDisponibil {
    private BigDecimal stocDisponibil;
    private int produsId;

    public StocDisponibil( int produsId, BigDecimal stocDisponibil) {
        this.stocDisponibil = stocDisponibil;
        this.produsId = produsId;
    }

}
