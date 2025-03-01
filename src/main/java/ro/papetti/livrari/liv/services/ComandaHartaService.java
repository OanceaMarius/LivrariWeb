/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.liv.services;

import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.ComandaHarta;

import java.util.Optional;

/**
 *
 * @author MariusO
 */
public interface ComandaHartaService {

    @Transactional
    Optional<ComandaHarta> getComandaHartaById(int capId);
}
