/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import java.util.Optional;

import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.PorderPozDto;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.SorderCap;

/**
 *
 * @author MariusO
 */
public interface PorderPozService extends BaseService<PorderPoz, Integer> {
    
     List<PorderPoz> findEagerByPorderCapId(int pOrderCapId);

    Optional<PorderPoz> findEagerById(int porderPozId);

    <T>List<T> findPozDTOIByPOrderCapId(int pOrderCapId, Class<T> type);


    Optional<SorderCapDto> findSorderCapFaraPozitiiDtoBySorderPozId(int sorderPozId);

    List<PorderPozDto> findPozDtoByPOrderCapId(int pOrderCapId);


}
