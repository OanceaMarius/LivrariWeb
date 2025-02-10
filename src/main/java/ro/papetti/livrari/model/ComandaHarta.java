/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.utilitare.UtilComenzi;
import ro.papetti.pluriva.dto.SOrderPozDTO;
import ro.papetti.pluriva.dto.SOrderPozDTOI;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.Unitate;

/**
 *
 * @author MariusO
 */
public class ComandaHarta extends ComandaCap implements Serializable {

    private List<ComandaPluPoz>  pozitiiPluriva = new ArrayList<>();
    private Unitate unitate;
    private String plata;
   

    public ComandaHarta(ComandaCap comandaCap) {
        BeanUtils.copyProperties(comandaCap, this);
    }

    public List<ComandaPluPoz> getPozitiiPluriva() {
        return pozitiiPluriva;
    }

    public void setPozitiiPluriva(List<ComandaPluPoz> pozitiiPluriva) {
        this.pozitiiPluriva = pozitiiPluriva;
    }

    public String getPlata() {
        return plata;
    }

    public void setPlata(String plata) {
        this.plata = plata;
    }




  

    public void setPozPluFromSOrder(List<SOrderPozDTO> listSOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromSDTO(listSOrderPoz)    ;
    }
    
        public void setPozPluFromSOrderI(List<SOrderPozDTOI> listSOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromSDTOI(listSOrderPoz)    ;
    }
    
    public void setPozPluFromPOrder(List<POrderPoz> listPOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromP(listPOrderPoz)    ;
    }
    
    public Unitate getUnitate() {
        return unitate;
    }

    public void setUnitate(Unitate unitate) {
        this.unitate = unitate;
    }

    public ComandaHarta(){}

    public ComandaHarta(Unitate unitate, String plata, Integer capId, int iDMasina, int tipLivrareId, int firmaId, int orderCapId, short indexCom, short ordineLiv, String com, boolean vizitat, boolean anulata, Date momentIntr) {
        super(capId, iDMasina, tipLivrareId, firmaId, orderCapId, indexCom, ordineLiv, com, vizitat, anulata, momentIntr);
        this.unitate = unitate;
        this.plata = plata;
    }
    
    

 
}
