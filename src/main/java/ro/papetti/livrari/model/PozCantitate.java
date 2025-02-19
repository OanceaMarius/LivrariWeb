/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.model;

import lombok.*;

import java.math.BigDecimal;

/**
 *
 * @author MariusO
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class PozCantitate {
    private int pozId;
    private BigDecimal cantitate;




}
