/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;
import ro.papetti.pluriva.entity.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author MariusO
 */
@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class PlurivaRestController {
    
    //ca sa introduc data in formatul dorit de mine
    @InitBinder
    public void initBinder(@org.jetbrains.annotations.NotNull WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    
    private final SOrderCapService sOrderCapSer;
    private final SOrderPozService sOrderPozSer;
    private final POrderCapService pOrderCapSer;
    private final POrderPozService pOrderPozSer;    
    private final FollowUpService follwUpSer;
    private final StocService stocService;
    private final UserService userService;
    private final TvaService tvaService;

    public PlurivaRestController(
            SOrderPozService sOrderPozSer, SOrderCapService sOrderCapSer,
            POrderCapService pOrderCapSer, POrderPozService pOrderPozSer,
            FollowUpService follwUpSer, StocService stocService, UserService userService, TvaService tvaService) {
        this.sOrderPozSer = sOrderPozSer;
        this.sOrderCapSer = sOrderCapSer;
        this.pOrderCapSer=pOrderCapSer;
        this.pOrderPozSer=pOrderPozSer;
        this.follwUpSer=follwUpSer;
        this.stocService = stocService;
        this.userService = userService;
        this.tvaService = tvaService;
    }
    
    @GetMapping("/SOrderCap/{sOrderCapId}")
    public ResponseEntity<SOrderCap> findSOrderCapById(@PathVariable int sOrderCapId){
        SOrderCap cap = sOrderCapSer.findById(sOrderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc SOrderCap cu SorderId: "+sOrderCapId));
        return ResponseEntity.ok(cap);
    }
    
    @GetMapping("/SOrderCapDTO/{sOrderCapId}")
    public ResponseEntity<SOrderCapDTOI> findDTOById(@PathVariable int sOrderCapId){
        SOrderCapDTOI cap = sOrderCapSer.findDTOById(sOrderCapId).orElseThrow(()->new EntityNotFoundException("Nu gasesc SOrderCap cu SorderId: "+sOrderCapId));
        return ResponseEntity.ok(cap);
    }

    /**
     * 
     * @param dataLivrarii in format yyyy-mm-dd
     * @return 
     */
    @GetMapping("/SOrderCap/ByDataLivrarii/{dataLivrarii}")
    public List<SOrderCap> findSOrderCapByDataLivrarii(@PathVariable Date dataLivrarii){
        return sOrderCapSer.findByDataLivrare(dataLivrarii).orElse(new ArrayList<SOrderCap>());
    }

    @Transactional
    @GetMapping("/SOrderPozDTO/ByCapId/{sOrderCapId}")
    public List<SOrderPozDTOI> findSOrderPozDTOByCapId(@PathVariable int sOrderCapId){
        return sOrderPozSer.findPozitiiDTOBySOrderCapId(sOrderCapId);
    }
    
       @Transactional
    @GetMapping("/SOrderPoz/ByCapId/{sOrderCapId}")
    public List<SOrderPoz> findSOrderPozByCapId(@PathVariable int sOrderCapId){
        return sOrderPozSer.findPozitiiBySOrderCapId(sOrderCapId);
    }
    
    @GetMapping("/POrderPozDTO/ByCapId/{pOrderCapId}")
    public List<POrderPozDTOI> findSOrderPozDTOBySOrderCapId(@PathVariable int pOrderCapId){
        return pOrderPozSer.findPozitiiDTOByPOrderCapId(pOrderCapId, POrderPozDTOI.class);
    }
    
    
    @GetMapping("/POrderPoz/ByCapId/{pOrderCapId}")
    public List<POrderPoz> findPOrderPozByCapId(@PathVariable int pOrderCapId){
        return pOrderPozSer.findPozitiiByPOrderCapId(pOrderCapId);
    }
    
    //studiu
    @Transactional
    @GetMapping("/POrderPozByCapId/{pOrderCapId}")
    public List<POrderPoz> findPOrderPoz_CapId(@PathVariable int pOrderCapId){
        return  pOrderCapSer.findPOrderPozByPOrderCapId(pOrderCapId);


    }
    
    @GetMapping("/POrderCap/{pOrderCapId}")
    public ResponseEntity<POrderCap> findPOrderCapById(@PathVariable int pOrderCapId){
        POrderCap cap =  pOrderCapSer.findById(pOrderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc POrderCap cu POrderCapId: "+pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
        return ResponseEntity.ok(cap);
    }
    
        @GetMapping("/POrderCapDTO/{pOrderCapId}")
    public ResponseEntity<POrderCapDTOI> findDTOByPOrderCapId(@PathVariable int pOrderCapId){
        POrderCapDTOI cap =  pOrderCapSer.findDTOByPOrderCapId(pOrderCapId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc POrderCap cu POrderCapId: "+pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
            Hibernate.initialize(cap.getFurnizorUnitate());
        return ResponseEntity.ok(cap);
    }


    @GetMapping("/POrderCap/ByDataLivrarii/{dataLivrarii}")
    public List<POrderCap> findPOrderCapByDataLivrarii(@PathVariable Date dataLivrarii){
        return pOrderCapSer.findByDataLivrare(dataLivrarii);
    }
    
    

    
    @GetMapping("/followUp/{followUpId}")
    public Optional<FollowUp> findFollowUpById(@PathVariable int followUpId){
        return follwUpSer.findById(followUpId);
    }
    
    @GetMapping("/StocDisponibil/{firmaId}/{gestiuneId}")
    public List<StocDisponibil> getStocDisponibil(@PathVariable int firmaId, @PathVariable int gestiuneId){
        return stocService.getStocDisponibilInGestiune(firmaId, gestiuneId);
    }
    
    @GetMapping("/StocDisponibil/{firmaId}")
    public Set<StocDisponibil>  getStocDisponibilOperational(@PathVariable int firmaId){
        return stocService.getStocDisponibilInGestiuneOperationala(firmaId);
    }
    
    @GetMapping("/SCantLivrate/{sOrderCapId}/{firmaId}")
    public List<PozCantitate>  getCantitatiLivrate(@PathVariable int sOrderCapId, @PathVariable int firmaId){
        return sOrderCapSer.getCantitatiLivrate(sOrderCapId, firmaId);
    }
    
    @GetMapping("/SCantRezervate/{sOrderCapId}")
    public List<PozCantitate>  getCantitatiRezervate(@PathVariable int sOrderCapId){
        return sOrderCapSer.getCantitatiRezervate(sOrderCapId);
    }

    @GetMapping("/User")
    public List<User> findUserAll(){
        return  userService.findAll();
    }

    @GetMapping("/User/{userId}")
    public Optional<User> findUserById(@PathVariable int userId){
        return  userService.findById(userId);
    }

    @GetMapping("/UserDTO")
    public List<UserDTOI> findDTOUserAll(){
        return  userService.findDTOAll(UserDTOI.class);
    }

    @GetMapping("/UserDTO/{userId}")
    public Optional<UserDTOI> findDTOUserById(@PathVariable int userId){
        return  userService.findDTOById(userId, UserDTOI.class);
    }

    @GetMapping("/UserDTOCache")
    public List<UserDTOI> findUserDTOAllCache(){
        return  userService.findDTOAllCache(UserDTOI.class);
    }

    @GetMapping("/UserDTOCache/{userId}")
    public Optional<UserDTOI> findDTOUserByIdCache(@PathVariable int userId){
        return  userService.findDTOByIdCache(userId, UserDTOI.class);
    }





}
