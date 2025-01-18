/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.services.ComandaAntetProgramService;


/**
 *
 * @author MariusO
 */
@RestController
@RequestMapping("cap")
public class LivrariController {
        
    @Autowired
    private ComandaAntetProgramService capSer;
    
    @GetMapping
//    public TblComandaAntetProgram getAntetProgram(){
//        return capSer.getById(676365);
//    }
    public String getAntet(){
        
        System.out.println("Salut");
        return "Salut de doua ori!";

    }
    
}
