/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import ro.papetti.PlurivaTabele.entity.POrderPoz;
import ro.papetti.PlurivaTabele.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
@AllArgsConstructor
public @Data class ComandaPluPoz implements Serializable {
    private int orderCapId;
    private int orderPozId;
    private int produsId;
    private BigDecimal cantPlu;
    private BigDecimal pretPlu;

    public ComandaPluPoz(SOrderPoz pozPlu) {
        this.orderCapId = pozPlu.getSOrderCapId();
        this.orderPozId = pozPlu.getSOrderPozId();
        this.produsId=pozPlu.getProdusId();
        this.cantPlu= pozPlu.getCant();
        this.pretPlu=pozPlu.getPretValuta();
    }
    
    public ComandaPluPoz(POrderPoz pozPlu) {
        this.orderCapId = pozPlu.getPOrderCapId();
        this.orderPozId = pozPlu.getPOrderPozId();
        this.produsId=pozPlu.getProdusId();
        this.cantPlu= pozPlu.getCant();
        this.pretPlu=pozPlu.getPretValuta();
    }


}
