/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author MariusO
 */
@Data
public class ProdusCantitate {
    private BigDecimal cantitate;
    private int produsId;

    public ProdusCantitate(int produsId, BigDecimal cantitate) {
        this.cantitate = cantitate;
        this.produsId = produsId;
    }

}
