/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controllers.rest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.liv.services.ComandaPozService;


/**
 *
 * @author MariusO
 */
@RestController
@RequestMapping("/api")
public class LivrariRestController {
        
    private final ComandaCapService capSer;
    private final ComandaPozService liniiSer;

    public LivrariRestController(ComandaCapService capSer, ComandaPozService liniiProSer) {
        this.capSer = capSer;
        this.liniiSer = liniiProSer;
    }
    
    @GetMapping("/ComenziCap/{idCap}")
    public ComandaCap getComadaCapById(@PathVariable int idCap){
        return capSer.findById(idCap).get();
    }
    
    @GetMapping("/ComenziCap")
    public List<ComandaCap> getAllComenziCap(){
        
        return capSer.findAll();
    }
    
//    @GetMapping("/liniiProgram/{idLinie}")
//    public TblComandaLiniiProgram getLinieProgramById(@PathVariable int idLinie){
//        return liniiProSer.findById(idLinie).get();
//    }
    
    @GetMapping("/ComenziPoz/{idCap}")
    public List<ComandaPoz> getComenziPozByIdCap(@PathVariable int idCap){
        
        return liniiSer.findComenziPozByIdCap(idCap);
    }
      
//    @GetMapping("/liniiProgram/proc/{nr}")
//    public List<String> getProcedure(@PathVariable int nr){
//        return liniiProSer.getTestProc(nr);
//    }
}
