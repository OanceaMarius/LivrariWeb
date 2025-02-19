/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.List;

import ro.papetti.pluriva.dtoi.PorderPozDTOI;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.livrari.model.BaseService;

/**
 *
 * @author MariusO
 */
public interface PorderPozService extends BaseService<PorderPoz, Integer> {
    
     List<PorderPoz> findPozitiiByPOrderCapId(int pOrderCapId);
    
     <T>List<T> findPozitiiDTOByPOrderCapId(int pOrderCapId, Class<T> type);


    
    
}
