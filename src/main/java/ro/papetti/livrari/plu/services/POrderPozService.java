/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import java.util.Optional;
import ro.papetti.pluriva.dto.POrderPozDTOI;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.livrari.model.BaseService;

/**
 *
 * @author MariusO
 */
public interface POrderPozService extends BaseService<POrderPoz, Integer> {
    
    public List<POrderPoz> findPozitiiByPOrderCapId(int pOrderCapId);
    
    public List<POrderPozDTOI> findPozitiiDTOByPOrderCapId(int pOrderCapId);

    Optional<POrderPoz> findById(Integer id);
    
    
}
