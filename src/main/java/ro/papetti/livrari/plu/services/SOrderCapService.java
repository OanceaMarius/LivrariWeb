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
    
    Optional<SOrderCap> findById(Integer sOrderCapId);

    Optional<SOrderCap> findByIdCuPozitii(int sOrderCapId);

    Optional<SOrderCap> findByIdCuClient(int sOrderCapId);

    Optional<SOrderCapDTOI> findDTOById(int sOrderCapId);

    Optional<SOrderCapDTOI> findDTOByIdCuClient(int sOrderCapId);

    Optional<SOrderCapDTOI> findDTOByIdCuPozitii(int sOrderCapId);

    Optional<SOrderCapDTOI> findDTOByIdCuPozitiiSiLegaturaLaAprov(int sOrderCapId);

    Optional<SOrderCap> findById(int sOrderCapId);

    Optional<SOrderCap> findByIdCuPozitiiSiLegaturaLaAprov(int sOrderCapId);
}
