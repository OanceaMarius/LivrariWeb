/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.liv.services;

import java.util.List;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.livrari.model.BaseService;

/**
 *
 * @author MariusO
 */
public interface ComandaPozService extends BaseService<ComandaPoz, Integer> {
    List<ComandaPoz> findComenziPozByIdCap(int idProgram);
}
