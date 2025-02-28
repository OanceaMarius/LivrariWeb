/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.dtoi.PorderCapDTOI;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.entity.SorderCap;

/**
 *
 * @author MariusO
 */
@Transactional
public interface PorderCapService extends BaseService<PorderCap, Integer> {


    public List<PorderPoz> findPOrderPozByPOrderCapId(int pOrderCapId);
    

    public List<PorderCap> findByDataLivrare(Date dataLivrare);
    
    
    public Optional<PorderCap> findById(Integer pOrderCapId);
    
    public Optional<PorderCapDTOI> findDTOIByPOrderCapId(Integer pOrderCapId);

    Optional<PorderCap> findEagerById(int porderCapId);

    Optional<PorderCapDto> findDtoById(int porderCapId);


}