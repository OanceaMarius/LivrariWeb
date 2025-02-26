/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.dto.SorderPozDto;
import ro.papetti.pluriva.dtoi.SorderPozDTOI;
import ro.papetti.pluriva.entity.SorderPoz;

/**
 *
 * @author MariusO
 */
public interface SorderPozService extends BaseService<SorderPoz, Integer> {

    List<SorderPoz> findPozitiiBySOrderCapId(int sOrderCapId);

    List<SorderPozDTOI> findPozitiiDTOBySOrderCapId(int sOrderCapId);

    List<SorderPozDTOI> findPozitiiDTOBySOrderCapIdCUProduse(int sOrderCapId);

    List<SorderPozDTOI> findPozitiiDTOBySOrderCapIdCuProduseSiFurnizori(int sOrderCapId);

    Optional<SorderCapDto> findSorderCapDtoBySorderPozId(int sorderPozId);

    Optional<SorderPoz> findEagerById(int sorderPozId);

    List<SorderPoz> findEagerBySorderCapId(@NonNull int sorderCapId);

    List<SorderPozDto> findPozDtoBySOrderCapId(int sorderCapId);
}
