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

    Optional<SorderCap> findByIdCuPozitii(int sOrderCapId);

    Optional<SorderCap> findByIdCuClient(int sOrderCapId);

    Optional<SorderCapDTOI> findDTOById(int sOrderCapId);

    Optional<SorderCapDTOI> findDTOByIdCuClient(int sOrderCapId);

    Optional<SorderCapDTOI> findDTOByIdCuPozitii(int sOrderCapId);

    Optional<SorderCapDTOI> findDTOByIdCuPozitiiSiLegaturaLaAprov(int sOrderCapId);

    Optional<SorderCap> findById(int sOrderCapId);

    Optional<SorderCap> findByIdCuPozitiiSiLegaturaLaAprov(int sOrderCapId);
}
