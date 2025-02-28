/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.dtoi.SorderCapDTOI;
import ro.papetti.pluriva.entity.SorderCap;

/**
 *
 * @author MariusO
 */
public interface SorderCapService extends BaseService<SorderCap, Integer> {

    Optional<List<SorderCap>> findByDataLivrare(Date dataLivrare);
    
    List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId);
    
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId);
    
    Optional<SorderCap> findById(Integer sOrderCapId);

    Optional<SorderCap> findEagerById(int sorderCapId);

    Optional<SorderCap> findByIdCuClient(int sOrderCapId);

    Optional<SorderCapDTOI> findDTOIById(int sOrderCapId);

    Optional<SorderCapDTOI> findDTOIByIdCuClient(int sOrderCapId);

    Optional<SorderCapDTOI> findDTOIByIdCuPozitii(int sOrderCapId);


    Optional<SorderCapDto> findDtoById(int sorderCapId);


    Optional<SorderCapDto> findSorderCapFaraPozitiiDtoBySorderPozId(int sorderPozId);

    Optional<SorderCap> findById(int sOrderCapId);

}
