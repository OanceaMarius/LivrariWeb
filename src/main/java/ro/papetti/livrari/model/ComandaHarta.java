/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.BeanUtils;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.utilitare.UtilComenzi;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.SOrderPoz;
import ro.papetti.pluriva.entity.Unitate;

/**
 *
 * @author MariusO
 */
public class ComandaHarta extends ComandaCap implements Serializable {
//    private ComandaCap capLiv;
//    private List<ComandaPoz> pozLiv; 
    private List<ComandaPluPoz>  pozitiiPluriva;
    private Unitate unitate;
   

    public ComandaHarta(ComandaCap comandaCap) {
        BeanUtils.copyProperties(comandaCap, this);
    }

    public List<ComandaPluPoz> getPozitiiPluriva() {
        return pozitiiPluriva;
    }

    public void setPozitiiPluriva(List<ComandaPluPoz> pozitiiPluriva) {
        this.pozitiiPluriva = pozitiiPluriva;
    }




  

    public void setPozPluFromSOrder(List<SOrderPoz> listSOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromS(listSOrderPoz)    ;
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
    
    

 
}
