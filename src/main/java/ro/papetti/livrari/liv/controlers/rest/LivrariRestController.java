/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.controlers.rest;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.liv.services.ComandaPozService;
import ro.papetti.livrari.liv.services.jpa.ComandaCapServiceImpl;
import ro.papetti.livrari.liv.services.jpa.ComandaPozServiceImpl;


/**
 *
 * @author MariusO
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LivrariRestController {
        
    private final ComandaCapService comandaCapService;
    private final ComandaPozService comandaPozService;

    @GetMapping("/ComenziCap/{idCap}")
    public ComandaCap getComadaCapById(@PathVariable int idCap){
        return comandaCapService.findById(idCap).get();
    }
    
    @GetMapping("/ComenziCap")
    public List<ComandaCap> getAllComendaCap(){
        
        return comandaCapService.findAll();
    }
    
//    @GetMapping("/liniiProgram/{idLinie}")
//    public TblComandaLiniiProgram getLinieProgramById(@PathVariable int idLinie){
//        return liniiProSer.findById(idLinie).get();
//    }
    
    @GetMapping("/ComenziPoz/{idCap}")
    public List<ComandaPoz> getComenziPozByIdCap(@PathVariable int idCap){
        
        return comandaPozService.findComenziPozByIdCap(idCap);
    }
      
//    @GetMapping("/liniiProgram/proc/{nr}")
//    public List<String> getProcedure(@PathVariable int nr){
//        return liniiProSer.getTestProc(nr);
//    }
}
