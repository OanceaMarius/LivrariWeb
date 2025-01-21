/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.controller.rest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.LivrariTabele.entity.TblComandaAntetProgram;
import ro.papetti.LivrariTabele.entity.TblComandaLiniiProgram;
import ro.papetti.livrari.services.ComandaAntetProgramService;
import ro.papetti.livrari.services.ComandaLiniiProgramService;


/**
 *
 * @author MariusO
 */
@RestController
@RequestMapping("/api")
public class LivrariRestController {
        
    private final ComandaAntetProgramService antetProSer;
    private final ComandaLiniiProgramService liniiProSer;

    public LivrariRestController(ComandaAntetProgramService capSer, ComandaLiniiProgramService liniiProSer) {
        this.antetProSer = capSer;
        this.liniiProSer = liniiProSer;
    }
    
    @GetMapping("/anteteProgram/{idProgram}")
    public TblComandaAntetProgram getAntetProgramById(@PathVariable int idProgram){
        return antetProSer.findById(idProgram).get();
    }
    
    @GetMapping("/anteteProgram")
    public List<TblComandaAntetProgram> getAllAnteteProgram(){
        
        return antetProSer.findAll();
    }
    
//    @GetMapping("/liniiProgram/{idLinie}")
//    public TblComandaLiniiProgram getLinieProgramById(@PathVariable int idLinie){
//        return liniiProSer.findById(idLinie).get();
//    }
    
    @GetMapping("/liniiProgram/{IdProgram}")
    public List<TblComandaLiniiProgram> getLiniiProgramByIdProgram(@PathVariable int IdProgram){
        
        return liniiProSer.findLiniiByIdProgram(IdProgram);
    }
      
}
