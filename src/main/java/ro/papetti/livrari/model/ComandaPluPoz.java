/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import lombok.Data;
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
@Data
public class ComandaPluPoz implements Serializable {

    private Integer orderPozId;
    private Integer orderPozPluId;
    private BigDecimal cantRepartizata = BigDecimal.valueOf(0);
    private BigDecimal cantStoc = BigDecimal.valueOf(0);    /* TODO sa aduc stocul */
    private BigDecimal cantLivrata = BigDecimal.valueOf(0); /* TODO sa aduc cantitatealivrata/receptionata */
    private BigDecimal cantRezervat = BigDecimal.valueOf(0);   // TODO: sa aduc cantitatea rezervata pt comanda curenta
    private BigDecimal cantComFurnizor = BigDecimal.valueOf(0); /* TODO sa aduc cant comandata la furnizor */
    private BigDecimal cantComFurnizorStoc = BigDecimal.valueOf(0);  /* TODO cant comandata la furnizor pt stoc  */
    private BigDecimal cantFacturata = BigDecimal.valueOf(0);  // TODO: sa aduc cantitatea facturata


}