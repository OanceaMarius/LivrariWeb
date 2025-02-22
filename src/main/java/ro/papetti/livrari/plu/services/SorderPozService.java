/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;
import ro.papetti.livrari.model.BaseService;
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
    
}
