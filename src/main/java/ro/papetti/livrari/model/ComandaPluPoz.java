/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.io.Serializable;
import java.math.BigDecimal;
import ro.papetti.pluriva.dto.SOrderPozDTO;
import ro.papetti.pluriva.dto.SOrderPozDTOI;
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

    private Integer orderPozId;
    private Integer produsId;
    private Integer orderPozAsociatId;
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

    public ComandaPluPoz(SOrderPoz pozPlu) {

//        this.orderCapId = pozPlu.getSOrderCap().getSOrderCapId();
        this.orderPozId = pozPlu.getsOrderPozId();
        this.produsId = pozPlu.getProdus()
                .map(Produs::getProdusId).get();
        this.orderPozAsociatId = pozPlu.getpOrderPoz()
                .map(POrderPoz::getpOrderPozId).orElse(0);
        this.denumirePartenerAsociat = pozPlu.getpOrderPoz()
                .map(POrderPoz::getPOrderCap)
                .map(POrderCap::getFurnizorUnitate)
                .map(Unitate::getDenumireUnitateCompleta).orElse(null);
        this.numarComAsociata = pozPlu.getpOrderPoz()
                .map(POrderPoz::getPOrderCap)
                .map(POrderCap::getpOrderNumber)
                .orElse(null);

        this.denumireProdus = pozPlu.getProdus()
                .map(Produs::getDenumireProdus)
                .orElse("Negasit");
        this.cantPlu = pozPlu.getCant();
        this.pretPlu = pozPlu.getPretValuta();

    }

    public ComandaPluPoz(SOrderPozDTO sPozPluDTO) {

        this.orderPozId = sPozPluDTO.sOrderPozId();
        this.produsId = sPozPluDTO.produs().getProdusId();
        if (sPozPluDTO.pOrderPoz() != null) {
            this.orderPozAsociatId = sPozPluDTO.pOrderPoz().getpOrderPozId();
            this.denumirePartenerAsociat = sPozPluDTO.pOrderPoz()
                    .getPOrderCap()
                    .getFurnizorUnitate()
                    .getDenumireUnitateCompleta();
            this.numarComAsociata = sPozPluDTO.pOrderPoz()
                    .getPOrderCap()
                    .getpOrderNumber();

        }
    }

    public ComandaPluPoz(SOrderPozDTOI sPozPluDTOI) {

        this.orderPozId = sPozPluDTOI.getsOrderPozId();
        this.produsId = sPozPluDTOI.getProdus().getProdusId();
        if (sPozPluDTOI.getpOrderPoz() != null) {
            this.orderPozAsociatId = sPozPluDTOI.getpOrderPoz().getpOrderPozId();
            this.denumirePartenerAsociat = sPozPluDTOI.getpOrderPoz()
                    .getPOrderCap()
                    .getFurnizorUnitate()
                    .getDenumireUnitateCompleta();
            this.numarComAsociata = sPozPluDTOI.getpOrderPoz()
                    .getPOrderCap()
                    .getpOrderNumber();

        }

        this.denumireProdus = sPozPluDTOI.getProdus()
                .getDenumireProdus();
        this.cantPlu = sPozPluDTOI.getCant();
        this.pretPlu = sPozPluDTOI.getPretValuta();

    }

    //varianta noua
//        public ComandaPluPoz(SOrderPoz pozPlu) {
//        SOrderCap capPlu = pozPlu.getSOrderCap();
//        POrderPoz pozAsociatP = pozPlu.getPOrderPoz().orElse(null);
//        POrderCap capAsociatP = null;
//            if (pozAsociatP!=null) {
//                capAsociatP= pozAsociatP.getPOrderCap();
//            }
//        
//        this.orderCapId = capPlu.getSOrderCapId();
//        this.orderPozId = pozPlu.getSOrderPozId();
//        this.produsId=pozPlu.getProdus()
//                .map(Produs::getProdusId).get();
//            if (pozAsociatP!=null) {
//                this.orderPozAsociatId = pozAsociatP.getPOrderPozId();
//                this.denumirePartenerAsociat= capAsociatP.getFurnizorUnitate()
//                        .getDenumireUnitateCompleta();
//                this.numarComAsociata=capAsociatP.getPOrderNumber();
//            }
//
//
//        this.denumireProdus= pozPlu.getProdus()
//                .map(Produs::getDenumireProdus)
//                .orElse("Negasit");
//        this.cantPlu= pozPlu.getCant();
//        this.pretPlu=pozPlu.getPretValuta();
//
//    }
//    public ComandaPluPoz(POrderPoz pozPlu) {
//        this.orderCapId = pozPlu.getPOrderCap().getPOrderCapId();
//        this.orderPozId = pozPlu.getPOrderPozId();
//        this.produsId=pozPlu.getProdus()
//                .map(Produs::getProdusId).orElse(null);
//        this.orderPozAsociatId = pozPlu.getSOrderPoz()
//                .map(SOrderPoz::getSOrderPozId)
//                .orElse(0);
//        this.denumirePartenerAsociat= pozPlu.getSOrderPoz()
//                .map(SOrderPoz::getSOrderCap)
//                .map(SOrderCap::getClient)
//                .map(Unitate::getDenumireUnitateCompleta)
//                .orElse(null);
//        this.numarComClientAsociata=pozPlu.getSOrderPoz()
//                .map(SOrderPoz::getSOrderCap)
//                .map(SOrderCap::getSOClientNumber)
//                .orElse(null);
//        this.numarComAsociata=pozPlu.getSOrderPoz()
//                .map(SOrderPoz::getSOrderCap)
//                .map(SOrderCap::getSOrderNumber)
//                .orElse(null);
//        this.denumireProdus= pozPlu.getProdus()
//                .map(Produs::getDenumireProdus)
//                .orElse("Negasit");
//        this.cantPlu= pozPlu.getCant();
//        this.pretPlu=pozPlu.getPretValuta();
//        this.orderPozAsociatId=pozPlu.getSOrderPoz()
//                .map(SOrderPoz::getSOrderPozId)
//                .orElse(0);
//        this.denumirePartenerAsociat=pozPlu.getSOrderPoz()
//                .map(SOrderPoz::getSOrderCap)
//                .map(SOrderCap::getClient)
//                .map(Unitate::getDenumireUnitateCompleta)
//                .orElse(null);
//                
//    }
    //varianta noua
    public ComandaPluPoz(POrderPoz pozPlu) {
//        this.orderCapId = pozPlu.getPOrderCap().getPOrderCapId();
        this.orderPozId = pozPlu.getpOrderPozId();
        this.produsId = pozPlu.getProdus()
                .map(Produs::getProdusId).orElse(null);
        SOrderPoz pozAsociatS = pozPlu.getSOrderPoz();
        if (pozAsociatS != null) {
            SOrderCap capAsociatS = pozAsociatS.getsOrderCap();
            this.orderPozAsociatId = pozAsociatS.getpOrderPozId();
            this.numarComClientAsociata = capAsociatS.getsOClientNumber();
            this.numarComAsociata = capAsociatS.getsOrderNumber();
            this.orderPozAsociatId = pozAsociatS.getsOrderPozId();
            this.denumirePartenerAsociat = capAsociatS.getClientLivrareUnitate().
                    orElse(capAsociatS.getClientUnitate()).
                    getDenumireUnitateCompleta();
        }

        this.denumireProdus = pozPlu.getProdus()
                .map(Produs::getDenumireProdus)
                .orElse("Negasit");
        this.cantPlu = pozPlu.getCant();
        this.pretPlu = pozPlu.getPretValuta();

    }

//    public int getOrderCapId() {
//        return orderCapId;
//    }
//
//    public void setOrderCapId(int orderCapId) {
//        this.orderCapId = orderCapId;
//    }
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

    public BigDecimal getCantStoc() {
        return cantStoc;
    }

    public void setCantStoc(BigDecimal cantStoc) {
        this.cantStoc = cantStoc;
    }

    public BigDecimal getCantRezervat() {
        return cantRezervat;
    }

    public void setCantRezervat(BigDecimal cantRezervat) {
        this.cantRezervat = cantRezervat;
    }

    public BigDecimal getCantFacturata() {
        return cantFacturata;
    }

    public void setCantFacturata(BigDecimal cantInActe) {
        this.cantFacturata = cantInActe;
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

    public BigDecimal getCantLivrata() {
        return cantLivrata;
    }

    public void setCantLivrata(BigDecimal cantLivrata) {
        this.cantLivrata = cantLivrata;
    }

}
