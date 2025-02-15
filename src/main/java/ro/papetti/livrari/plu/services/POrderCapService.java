/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.POrderCapDTOI;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;

/**
 *
 * @author MariusO
 */
public interface POrderCapService extends BaseService<POrderCap, Integer> {


    public List<POrderPoz> findPOrderPozByPOrderCapId(int pOrderCapId);
    

    public List<POrderCap> findByDataLivrare(Date dataLivrare);
    
    
    public Optional<POrderCap> findById(Integer pOrderCapId);
    
    public Optional<POrderCapDTOI> findDTOByPOrderCapId(Integer pOrderCapId);

    Optional<POrderCap> findByIdCuPozitii(Integer pOrderCapId);

    Optional<POrderCap> findByIdCuPozitiiSiLegaturaLaComenzi(Integer pOrderCapId);
}