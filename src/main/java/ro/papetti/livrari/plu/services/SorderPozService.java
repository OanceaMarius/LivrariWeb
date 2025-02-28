/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderPozDto;
import ro.papetti.pluriva.dtoi.SorderPozDTOI;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.SorderPoz;

/**
 *
 * @author MariusO
 */
public interface SorderPozService extends BaseService<SorderPoz, Integer> {

    List<SorderPoz> findPozitiiBySOrderCapId(int sOrderCapId);

    List<SorderPozDTOI> findPozitiiDTOIBySOrderCapId(int sOrderCapId);


    Optional<SorderPoz> findEagerById(int sorderPozId);

    List<SorderPoz> findEagerBySorderCapId(@NonNull int sorderCapId);

    List<SorderPozDto> findPozDtoBySOrderCapId(int sorderCapId);

    Optional<PorderCapDto> findPorderCapDtoFaraPozitiiByPorderPozId(int porderPozId);

}
