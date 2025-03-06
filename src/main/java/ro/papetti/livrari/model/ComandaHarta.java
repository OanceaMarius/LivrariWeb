/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.papetti.LivrariTabele.dto.ComandaCapDto;
import ro.papetti.LivrariTabele.dto.CoordonateFixeDto;
import ro.papetti.livrari.configs.formaters.DateFormater;
import ro.papetti.livrari.model.repartizare.RepAct_Pro;
import ro.papetti.livrari.model.repartizare.RepObi_Fix;
import ro.papetti.livrari.model.repartizare.RepPorder;
import ro.papetti.livrari.model.repartizare.RepSorder;
import ro.papetti.pluriva.dto.FollowUpDto;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.dto.UnitateDto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author MariusO
 */
@Component
@Setter
@Getter
public class ComandaHarta implements Serializable {
    @Getter(AccessLevel.NONE)
    private PorderCapDto porderCapDto;
    @Getter(AccessLevel.NONE)
    private SorderCapDto sorderCapDto;
    @Getter(AccessLevel.NONE)
    private FollowUpDto followUpDto;
    private CoordonateFixeDto coordonateFixeDto;
    private ComandaCapDto comandaCapDto;
    private List<ComandaPluPoz> comandaPluPozList;
    private UnitateDto unitateDto;
    private String dataComanda;
    private String dataLivrare;

    private static  DateFormater dateFormater; //= new DateFormater();

    @Autowired
    public ComandaHarta(DateFormater dateFormater) {
        ComandaHarta.dateFormater = dateFormater;
    }

    public ComandaHarta(FollowUpDto followUpDto, ComandaCapDto comandaCapDto) {
        this.followUpDto = followUpDto;
        this.comandaCapDto = comandaCapDto;
        this.unitateDto=followUpDto.getLeadDto().getUnitateClientDto();
        this.dataComanda=dateFormater.inFormatulDoarData(followUpDto.getDataCreare());
        this.dataLivrare=dateFormater.inFormatulDoarData(followUpDto.getDataFollowup());
        RepAct_Pro repActPro = new RepAct_Pro(comandaCapDto, followUpDto);
        repActPro.completeazaDatele();
    }

    public ComandaHarta(PorderCapDto porderCapDto, ComandaCapDto comandaCapDto) {
        this.porderCapDto = porderCapDto;
        this.comandaCapDto = comandaCapDto;
        this.unitateDto=porderCapDto.getFurnizorUnitateDto();
        System.out.println(porderCapDto.getPorderDate());
        this.dataComanda=dateFormater.inFormatulDoarData(porderCapDto.getPorderDate());
        this.dataLivrare=dateFormater.inFormatulDoarData(porderCapDto.getDataLivrare());
        RepPorder repPorder = new RepPorder(porderCapDto, comandaCapDto);
        comandaPluPozList = repPorder.getComandaPluPozList();
    }

    public ComandaHarta(SorderCapDto sorderCapDto, ComandaCapDto comandaCapDto) {
        this.sorderCapDto = sorderCapDto;
        this.comandaCapDto = comandaCapDto;
        this.unitateDto=sorderCapDto.getClientDto();
        this.dataComanda=dateFormater.inFormatulDoarData(sorderCapDto.getSorderDate());
        this.dataLivrare=dateFormater.inFormatulDoarData(sorderCapDto.getDataLivrare());
        RepSorder repSorder= new RepSorder(sorderCapDto, comandaCapDto);
        comandaPluPozList =repSorder.getComandaPluPozList();
    }

    public ComandaHarta(CoordonateFixeDto coordonateFixeDto, ComandaCapDto comandaCapDto) {
        this.coordonateFixeDto = coordonateFixeDto;
        this.comandaCapDto = comandaCapDto;
        RepObi_Fix repObiFix = new RepObi_Fix(coordonateFixeDto, comandaCapDto);
        repObiFix.completeazaDatele();
    }
}

