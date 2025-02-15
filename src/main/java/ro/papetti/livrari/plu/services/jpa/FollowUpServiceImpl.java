/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.FollowUpRepozitory;
import ro.papetti.livrari.plu.services.FollowUpService;
import ro.papetti.pluriva.entity.FollowUp;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class FollowUpServiceImpl extends BaseServiceImpl<FollowUp, FollowUpRepozitory>
        implements FollowUpService{

    public FollowUpServiceImpl(FollowUpRepozitory repozitory) {
        super(repozitory);
    }

}
