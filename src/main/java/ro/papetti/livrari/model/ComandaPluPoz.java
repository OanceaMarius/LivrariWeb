/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import lombok.Getter;
import lombok.Setter;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderPozDto;
import ro.papetti.pluriva.dtoi.SorderPozDTOI;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.entity.SorderCap;
import ro.papetti.pluriva.entity.SorderPoz;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author MariusO
 */
@Setter
@Getter
public class ComandaPluPoz implements Serializable {

    private Integer orderPozId;
    private Integer produsId;
    private Integer orderPozAsociatId = 0;
    private String denumirePartenerAsociat;
    private String numarComClientAsociata;
    private String numarComAsociata;
    private String denumireProdus;
    private BigDecimal pretPlu;
    private BigDecimal cantPlu;
    private BigDecimal cantStoc = BigDecimal.valueOf(0);
    private BigDecimal cantRezervat = BigDecimal.valueOf(0);   // TODO: sa aduc cantitatea rezervata pt comanda curenta
    private BigDecimal cantFacturata = BigDecimal.valueOf(0);  // TODO: sa aduc cantitatea facturata
    private BigDecimal cantLivrata = BigDecimal.valueOf(0);

}