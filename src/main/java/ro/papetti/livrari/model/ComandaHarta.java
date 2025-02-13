/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.utilitare.UtilComenzi;
import ro.papetti.pluriva.dto.SOrderPozDTOI;
import ro.papetti.pluriva.dto.UnitateDTOI;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.SOrderPoz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MariusO
 */
@Setter
@Getter
public class ComandaHarta extends ComandaCap implements Serializable {

    private List<ComandaPluPoz> pozitiiPluriva = new ArrayList<>();
    private UnitateDTOI unitate;
    private String plata;

    public ComandaHarta(ComandaCap comandaCap) {
        BeanUtils.copyProperties(comandaCap, this);
    }

    public void setPozPluFromSOrder(List<SOrderPoz> listSOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromS(listSOrderPoz);
    }

    public void setPozPluFromSOrderI(List<SOrderPozDTOI> listSOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromSDTOI(listSOrderPoz);
    }


    public void setPozPluFromPOrder(List<POrderPoz> listPOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromP(listPOrderPoz);
    }

    public ComandaHarta() {
    }

    public ComandaHarta(UnitateDTOI unitate, String plata, Integer capId, int iDMasina, int tipLivrareId,
                        int firmaId, int orderCapId, short indexCom, short ordineLiv, String com, boolean vizitat,
                        boolean anulata, Date momentIntr) {
        super(capId, iDMasina, tipLivrareId, firmaId, orderCapId, indexCom, ordineLiv, com, vizitat, anulata, momentIntr);
        this.unitate = unitate;
        this.plata = plata;
    }

}
