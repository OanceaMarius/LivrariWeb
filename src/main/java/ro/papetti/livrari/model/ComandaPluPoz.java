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

    public ComandaPluPoz(SorderPoz pozPlu) {

//        this.orderCapId = pozPlu.getSOrderCap().getSOrderCapId();
        this.orderPozId = pozPlu.getsOrderPozId();
        this.produsId = pozPlu.getProdus().getProdusId();
        PorderPoz pPoz = pozPlu.getPorderPoz();
        if (pPoz != null) {
            this.orderPozAsociatId = pPoz.getpOrderPozId();
            this.denumirePartenerAsociat = pPoz.getPorderCap()
                    .getFurnizorUnitate().getPartener().getDenumirePartener() + " " +  pPoz.getPorderCap()
                    .getFurnizorUnitate().getDenumireUnitate();
            this.numarComAsociata = pPoz.getPorderCap().getpOrderNumber();
        }


        this.denumireProdus = pozPlu.getProdus().getDenumireProdus();
        this.cantPlu = pozPlu.getCant();
        this.pretPlu = pozPlu.getPretValuta();

    }

//    public ComandaPluPoz(SorderPozDto sorderPozDto) {
//
//        this.orderPozId = sorderPozDto.getSOrderPozId();
//        this.produsId = sorderPozDto.getProdus().getProdusId();
//        if (sorderPozDto.getPorderPoz() != null) {
//            this.orderPozAsociatId = sorderPozDto.getPorderPoz().getPOrderPozId();
//            this.denumirePartenerAsociat = sorderPozDto.getPorderPoz()
//                    .getPorderCap()
//                    .getFurnizorUnitate()
//                    .getDenumireUnitateCompleta();
//            this.numarComAsociata = sorderPozDto.getPorderPoz()
//                    .getPorderCap()
//                    .getPOrderNumber();
//
//        }
//    }

    public ComandaPluPoz(SorderPozDto sorderPozDto) {

        this.orderPozId = sorderPozDto.getSOrderPozId();
        this.produsId = sorderPozDto.getProdus().getProdusId();
        if (sorderPozDto.getPorderPoz() != null) {
            this.orderPozAsociatId = sorderPozDto.getPorderPoz().getPOrderPozId();
            this.denumirePartenerAsociat = getDenumireUnitateCompleta(sorderPozDto.getPorderPoz().getPorderCap());
            this.numarComAsociata = sorderPozDto.getPorderPoz().getPorderCap().getPOrderNumber();

        }

        this.denumireProdus = sorderPozDto.getProdus()
                .getDenumireProdus();
        this.cantPlu = sorderPozDto.getCant();
        this.pretPlu = sorderPozDto.getPretValuta();

    }

    //varianta noua
    public ComandaPluPoz(PorderPoz pozPlu) {
//        this.orderCapId = pozPlu.getPOrderCap().getPOrderCapId();
        this.orderPozId = pozPlu.getpOrderPozId();
        this.produsId = pozPlu.getProdus().getProdusId();
        SorderPoz pozAsociatS = pozPlu.getSorderPoz();
        if (pozAsociatS != null) {
            SorderCap capAsociatS = pozAsociatS.getSorderCap();
            this.orderPozAsociatId = pozAsociatS.getpOrderPozId();
            this.numarComClientAsociata = capAsociatS.getsOClientNumber();
            this.numarComAsociata = capAsociatS.getsOrderNumber();
            this.orderPozAsociatId = pozAsociatS.getsOrderPozId();
            this.denumirePartenerAsociat = getDenumireUnitateCompleta(capAsociatS);

        this.denumireProdus = pozPlu.getProdus().getDenumireProdus();
        this.cantPlu = pozPlu.getCant();
        this.pretPlu = pozPlu.getPretValuta();

        }
    }

    private String getDenumireUnitateCompleta(PorderCap porederCap){
        String denumireCompleta = porederCap.getFurnizorUnitate().getPartener().getDenumirePartener();
        if (porederCap.getFurnizorUnitate().getDenumireUnitate()!=null && !porederCap.getFurnizorUnitate().getDenumireUnitate().isEmpty())
            denumireCompleta=denumireCompleta +" - " + porederCap.getFurnizorUnitate().getDenumireUnitate();

        return denumireCompleta;
    };

    private String getDenumireUnitateCompleta(PorderCapDto  porderCapDto){
        String denumireCompleta = porderCapDto.getFurnizorUnitate().getPartenerDto().getDenumirePartener();
        if (porderCapDto.getFurnizorUnitate().getDenumireUnitate()!=null && !porderCapDto.getFurnizorUnitate().getDenumireUnitate().isEmpty())
            denumireCompleta=denumireCompleta +" - " + porderCapDto.getFurnizorUnitate().getDenumireUnitate();

        return denumireCompleta;
    };

    private String getDenumireUnitateCompleta(SorderCap sorderCap){
        String denumireCompleta = sorderCap.getClientUnitate().getPartener().getDenumirePartener();
        if (sorderCap.getClientUnitate().getDenumireUnitate()!=null && !sorderCap.getClientUnitate().getDenumireUnitate().isEmpty())
            denumireCompleta=denumireCompleta +" - " + sorderCap.getClientUnitate().getDenumireUnitate();

        return denumireCompleta;
    };

}
