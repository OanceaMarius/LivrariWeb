/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author MariusO
 */
@AllArgsConstructor
public @Data class Unitate {
    private int unitateId;
    private int partenerId;
    private String denumireUnitate;
    private String denumirePartener;
    private int tipFirmaId;
    private String codFiscal;
    private String atributFiscal;
    private int taraId;
    private String denumireTara;
    private int judetId;
    private String denumireJudet;
    private int localitateID;
    private String demunireLocalitate;
    private String siteWeb;
    private BigDecimal longitudine;
    private BigDecimal latitudine;

    public String getDenumireCompletaUnitate(){
        if (denumireUnitate == null)
            return denumirePartener ;
            
        return denumirePartener +" - "+ denumireUnitate;
    }
}
