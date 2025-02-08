/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import java.util.Date;
import java.util.List;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.POrderPoz;

/**
 *
 * @author MariusO
 */
public interface POrderCapService extends BaseService<POrderCap, Integer> {


    public List<POrderPoz> findPOrderPozByPOrderCapId(int pOrderCapId);
    

    public List<POrderCap> findByDataLivrare(Date dataLivrare);
}