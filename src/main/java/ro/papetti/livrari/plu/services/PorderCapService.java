/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dtoi.PorderCapDTOI;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.PorderPoz;

/**
 *
 * @author MariusO
 */
public interface PorderCapService extends BaseService<PorderCap, Integer> {


    public List<PorderPoz> findPOrderPozByPOrderCapId(int pOrderCapId);
    

    public List<PorderCap> findByDataLivrare(Date dataLivrare);
    
    
    public Optional<PorderCap> findById(Integer pOrderCapId);
    
    public Optional<PorderCapDTOI> findDTOByPOrderCapId(Integer pOrderCapId);

    Optional<PorderCap> findByIdCuPozitii(Integer pOrderCapId);

    Optional<PorderCap> findByIdCuPozitiiSiLegaturaLaComenzi(Integer pOrderCapId);
}