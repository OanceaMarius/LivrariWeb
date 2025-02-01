/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.io.Serializable;
import java.math.BigDecimal;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.Produs;
import ro.papetti.pluriva.entity.SOrderPoz;


/**
 *
 * @author MariusO
 */

public class ComandaPluPoz implements Serializable {
    private int orderCapId;
    private int orderPozId;
    private int produsId;
    private int sOrderPozId;
    private String denumirePartenerAsociat;// TODO: sa aduc informatia de nume partener
    private String numarComandaAsociata; // TODO:  sa aduc informatia de numar comanda asociata
    private String denumireProdus;
    private BigDecimal cantPlu;
    private BigDecimal pretPlu;
    private int cantStoc;       // TODO: sa aduc stocul
    private int cantRezervat;   // TODO: sa aduc cantitatea rezervata pt comanda curenta
    private int cantLivrat;  // TODO: sa aduc cantitatea livrata pt comanda curenta

    public ComandaPluPoz(SOrderPoz pozPlu) {
        this.orderCapId = pozPlu.getSOrderCapId();
        this.orderPozId = pozPlu.getSOrderPozId();
        this.produsId=pozPlu.getProdusId();
        this.denumireProdus= pozPlu.getProdus().map(Produs::getDenumireProdus).orElse("Negasit");
        this.cantPlu= pozPlu.getCant();
        this.pretPlu=pozPlu.getPretValuta();

     // TODO: sa pun legaturile intre comenzi si din partea comenzi de client
    }
    
    public ComandaPluPoz(POrderPoz pozPlu) {
        this.orderCapId = pozPlu.getPOrderCapId();
        this.orderPozId = pozPlu.getPOrderPozId();
        this.produsId=pozPlu.getProdusId();
        this.denumireProdus= pozPlu.getProdus().map(Produs::getDenumireProdus).orElse("Negasit");
        this.cantPlu= pozPlu.getCant();
        this.pretPlu=pozPlu.getPretValuta();
        this.sOrderPozId=pozPlu.getSOrderPozId();
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

    public int getCantLivrat() {
        return cantLivrat;
    }

    public void setCantLivrat(int cantLivrat) {
        this.cantLivrat = cantLivrat;
    }

    public String getDenumireProdus() {
        return denumireProdus;
    }

    public void setDenumireProdus(String denumireProdus) {
        this.denumireProdus = denumireProdus;
    }

    public int getsOrderPozId() {
        return sOrderPozId;
    }

    public void setsOrderPozId(int sOrderPozId) {
        this.sOrderPozId = sOrderPozId;
    }

    public String getDenumirePartenerAsociat() {
        return denumirePartenerAsociat;
    }

    public void setDenumirePartenerAsociat(String denumirePartenerAsociat) {
        this.denumirePartenerAsociat = denumirePartenerAsociat;
    }

    public String getNumarComandaAsociata() {
        return numarComandaAsociata;
    }

    public void setNumarComandaAsociata(String numarComandaAsociata) {
        this.numarComandaAsociata = numarComandaAsociata;
    }



 

}
