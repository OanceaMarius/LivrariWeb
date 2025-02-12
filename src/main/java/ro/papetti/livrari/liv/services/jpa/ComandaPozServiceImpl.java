/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.livrari.liv.repozitories.ComandaPozRepozitory;
import ro.papetti.livrari.liv.services.ComandaPozService;
import ro.papetti.livrari.model.BaseServiceImpl;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("livrariTransactionManager")
public class ComandaPozServiceImpl  extends BaseServiceImpl<ComandaPoz, ComandaPozRepozitory> implements ComandaPozService {

    public ComandaPozServiceImpl(ComandaPozRepozitory repozitory) {
        super(repozitory);
    }
   
    public List<ComandaPoz> findComenziPozByIdCap(int idProgram){
        return rep.findLiniiByIdProgram(idProgram);
    }

}
