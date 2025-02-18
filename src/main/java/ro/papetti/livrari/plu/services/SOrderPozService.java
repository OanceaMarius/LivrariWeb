/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.SOrderPozDTOI;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
public interface SOrderPozService extends BaseService<SOrderPoz, Integer> {

    List<SOrderPoz> findPozitiiBySOrderCapId(int sOrderCapId);

    List<SOrderPozDTOI> findPozitiiDTOBySOrderCapId(int sOrderCapId);

    List<SOrderPozDTOI> findPozitiiDTOBySOrderCapIdCUProduse(int sOrderCapId);

    List<SOrderPozDTOI> findPozitiiDTOBySOrderCapIdCuProduseSiFurnizori(int sOrderCapId);
    
}
