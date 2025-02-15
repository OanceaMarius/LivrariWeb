/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.liv.repozitories.ComandaCapRepozitory;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.model.BaseServiceImpl;


/**
 *
 * @author MariusO
 */
@Service
@Transactional("livrariTransactionManager")
public class ComandaCapServiceImpl  extends BaseServiceImpl<ComandaCap, ComandaCapRepozitory>  implements ComandaCapService{

    public ComandaCapServiceImpl(ComandaCapRepozitory repozitory) {
        super(repozitory);
    }


    /**
     *
     * @param capId
     * @return  Aduce si pozitiile de la comanda
     */
    @Override
    public Optional<ComandaCap> findByIdCuPozitii(Integer capId) {

        Optional<ComandaCap> comCap = rep.findById(capId);
        if (comCap.isPresent()) {
            Hibernate.initialize(comCap.get().getPozitiiLivrari());
            return comCap;
        }
        return Optional.empty();
    }
    
}
