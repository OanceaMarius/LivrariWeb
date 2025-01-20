/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.LivrariTabele.entity.TblComandaAntetProgram;
import ro.papetti.livrari.services.ComandaAntetProgramService;


/**
 *
 * @author MariusO
 */
@RestController
public class LivrariController {
        
    private ComandaAntetProgramService capSer;

    public LivrariController(ComandaAntetProgramService capSer) {
        this.capSer = capSer;
    }
    
    @GetMapping("/")
    public TblComandaAntetProgram getAntetProgram(){
        return capSer.findById(254317).get();
    }
    
    @GetMapping("/cap")
    public String getAntet(){
        
        System.out.println("Salut--");
        return "Salut de doua ori!--";

    }
    
    @RequestMapping("/th")
    public String getThymeLeaf(){
        return "test";
    }
    
}
