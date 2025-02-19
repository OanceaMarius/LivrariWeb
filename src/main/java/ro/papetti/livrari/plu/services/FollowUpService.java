/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ro.papetti.livrari.plu.services;

import ro.papetti.pluriva.dto.FollowUpDto;
import ro.papetti.pluriva.entity.FollowUp;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.PorderPoz;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author MariusO
 */
public interface FollowUpService extends BaseService<FollowUp, Integer> {

    Optional<FollowUpDto> findDtoById(int followupId);

    List<FollowUpDto> findDtoAll();
}
