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
import ro.papetti.pluriva.dto.SorderPozDto;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.entity.SorderPoz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MariusO
 */

@Setter
@Getter
public class ComandaHarta extends ComandaCap implements Serializable {

    private List<ComandaPluPoz> pozitiiPluriva = new ArrayList<>();

    public ComandaHarta(ComandaCap comandaCap) {
        BeanUtils.copyProperties(comandaCap, this);
    }

    public void setPozPluFromSOrder(List<SorderPoz> listSOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromS(listSOrderPoz);
    }

    public void setPozPluFromSOrderI(List<SorderPozDto> listSOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromSDto(listSOrderPoz);
    }


    public void setPozPluFromPOrder(List<PorderPoz> listPOrderPoz) {
        this.pozitiiPluriva = UtilComenzi.getComandaPluPozFromP(listPOrderPoz);
    }




}
