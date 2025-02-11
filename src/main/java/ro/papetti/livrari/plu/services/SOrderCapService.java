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
import ro.papetti.pluriva.dto.SOrderCapDTOI;
import ro.papetti.pluriva.entity.SOrderCap;

/**
 *
 * @author MariusO
 */
public interface SOrderCapService extends BaseService<SOrderCap, Integer> {

    Optional<List<SOrderCap>> findByDataLivrare(Date dataLivrare);
    
    List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId);
    
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId);
    
    public Optional<SOrderCapDTOI> findDTOBySOrderCapId(int sOrderCapId);

    Optional<SOrderCap> findById(Integer sOrderCapId);

    Optional<SOrderCap> findBySOrderCapId(int sOrderCapId);
}
