/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.io.Serializable;
import java.math.BigDecimal;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.Produs;
import ro.papetti.pluriva.entity.SOrderCap;
import ro.papetti.pluriva.entity.SOrderPoz;
import ro.papetti.pluriva.entity.Unitate;


/**
 *
 * @author MariusO
 */

public class ComandaPluPoz implements Serializable {
    private int orderCapId;
    private int orderPozId;
    private int produsId;
    private int orderPozAsociatId;
    private String denumirePartenerAsociat;
    private String numarComClientAsociata; 
    private String numarComAsociata;
    private String denumireProdus;
    private BigDecimal cantPlu;
    private BigDecimal pretPlu;
    private int cantStoc;       // TODO: sa aduc stocul
    private int cantRezervat;   // TODO: sa aduc cantitatea rezervata pt comanda curenta
    private int cantInActe;  // TODO: sa aduc cantitatea livrata pt comanda curenta

    public ComandaPluPoz(SOrderPoz pozPlu) {
        this.orderCapId = pozPlu.getSOrderCap().getSOrderCapId();
        this.orderPozId = pozPlu.getSOrderPozId();
        this.produsId=pozPlu.getProdus()
                .map(Produs::getProdusId).get();
        this.orderPozAsociatId = pozPlu.getPOrderPoz()
                .map(POrderPoz::getPOrderPozId).orElse(0);
        this.denumirePartenerAsociat= pozPlu.getPOrderPoz()
                .map(POrderPoz::getPOrderCap)
                .map(POrderCap::getFurnizorUnitate)
                .map(Unitate::getDenumireUnitateCompleta).orElse(null);
        this.numarComAsociata=pozPlu.getPOrderPoz()
                .map(POrderPoz::getPOrderCap)
                .map(POrderCap::getPOrderNumber)
                .orElse(null);

        this.denumireProdus= pozPlu.getProdus()
                .map(Produs::getDenumireProdus)
                .orElse("Negasit");
        this.cantPlu= pozPlu.getCant();
        this.pretPlu=pozPlu.getPretValuta();

    }
    
    public ComandaPluPoz(POrderPoz pozPlu) {
        this.orderCapId = pozPlu.getPOrderCap().getPOrderCapId();
        this.orderPozId = pozPlu.getPOrderPozId();
        this.produsId=pozPlu.getProdus()
                .map(Produs::getProdusId).orElse(null);
        this.orderPozAsociatId = pozPlu.getSOrderPoz()
                .map(SOrderPoz::getSOrderPozId)
                .orElse(0);
        this.denumirePartenerAsociat= pozPlu.getSOrderPoz()
                .map(SOrderPoz::getSOrderCap)
                .map(SOrderCap::getClient)
                .map(Unitate::getDenumireUnitateCompleta)
                .orElse(null);
        this.numarComClientAsociata=pozPlu.getSOrderPoz()
                .map(SOrderPoz::getSOrderCap)
                .map(SOrderCap::getSOClientNumber)
                .orElse(null);
        this.numarComAsociata=pozPlu.getSOrderPoz()
                .map(SOrderPoz::getSOrderCap)
                .map(SOrderCap::getSOrderNumber)
                .orElse(null);
        this.denumireProdus= pozPlu.getProdus()
                .map(Produs::getDenumireProdus)
                .orElse("Negasit");
        this.cantPlu= pozPlu.getCant();
        this.pretPlu=pozPlu.getPretValuta();
        this.orderPozAsociatId=pozPlu.getSOrderPoz()
                .map(SOrderPoz::getSOrderPozId)
                .orElse(0);
        this.denumirePartenerAsociat=pozPlu.getSOrderPoz()
                .map(SOrderPoz::getSOrderCap)
                .map(SOrderCap::getClient)
                .map(Unitate::getDenumireUnitateCompleta)
                .orElse(null);
                
    }

    public int getOrderCapId() {
        return orderCapId;
    }

    public void setOrderCapId(int orderCapId) {
        this.orderCapId = orderCapId;
    }

    public int getOrderPozId() {
        return orderPozId;
    }

    public void setOrderPozId(int orderPozId) {
        this.orderPozId = orderPozId;
    }

    public int getProdusId() {
        return produsId;
    }

    public void setProdusId(int produsId) {
        this.produsId = produsId;
    }

    public BigDecimal getCantPlu() {
        return cantPlu;
    }

    public void setCantPlu(BigDecimal cantPlu) {
        this.cantPlu = cantPlu;
    }

    public BigDecimal getPretPlu() {
        return pretPlu;
    }

    public void setPretPlu(BigDecimal pretPlu) {
        this.pretPlu = pretPlu;
    }

    public int getCantStoc() {
        return cantStoc;
    }

    public void setCantStoc(int cantStoc) {
        this.cantStoc = cantStoc;
    }

    public int getCantRezervat() {
        return cantRezervat;
    }

    public void setCantRezervat(int cantRezervat) {
        this.cantRezervat = cantRezervat;
    }

    public int getCantInActe() {
        return cantInActe;
    }

    public void setCantInActe(int cantInActe) {
        this.cantInActe = cantInActe;
    }



    public String getDenumireProdus() {
        return denumireProdus;
    }

    public void setDenumireProdus(String denumireProdus) {
        this.denumireProdus = denumireProdus;
    }

    public int getOrderPozAsociatId() {
        return orderPozAsociatId;
    }

    public void setOrderPozAsociatId(int orderPozAsociatId) {
        this.orderPozAsociatId = orderPozAsociatId;
    }



    public String getDenumirePartenerAsociat() {
        return denumirePartenerAsociat;
    }

    public void setDenumirePartenerAsociat(String denumirePartenerAsociat) {
        this.denumirePartenerAsociat = denumirePartenerAsociat;
    }

    public String getNumarComClientAsociata() {
        return numarComClientAsociata;
    }

    public void setNumarComClientAsociata(String numarComandaAsociata) {
        this.numarComClientAsociata = numarComandaAsociata;
    }

    public String getNumarComAsociata() {
        return numarComAsociata;
    }

    public void setNumarComAsociata(String numarComAsociata) {
        this.numarComAsociata = numarComAsociata;
    }



 

}
