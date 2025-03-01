/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.CoordonateFixe;
import ro.papetti.livrari.utilitare.UtilComenzi;
import ro.papetti.pluriva.dto.FollowUpDto;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderCapDto;
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
public class ComandaHarta implements Serializable {

    private PorderCapDto porderCapDto;
    private SorderCapDto sorderCapDto;
    private FollowUpDto followUpDto;
    private CoordonateFixe coordonateFixe;
    private ComandaCap comandaCap;

    public ComandaHarta(FollowUpDto followUpDto, ComandaCap comandaCap) {
        this.followUpDto = followUpDto;
        this.comandaCap = comandaCap;
    }

    public ComandaHarta(PorderCapDto porderCapDto, ComandaCap comandaCap) {
        this.porderCapDto = porderCapDto;
        this.comandaCap = comandaCap;
    }

    public ComandaHarta(SorderCapDto sorderCapDto, ComandaCap comandaCap) {
        this.sorderCapDto = sorderCapDto;
        this.comandaCap = comandaCap;
    }

    public ComandaHarta(CoordonateFixe coordonateFixe, ComandaCap comandaCap) {
        this.coordonateFixe = coordonateFixe;
        this.comandaCap = comandaCap;
    }
}
