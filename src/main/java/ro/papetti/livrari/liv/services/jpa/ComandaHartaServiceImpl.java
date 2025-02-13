/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import java.util.Optional;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.componente.InfoMarfa;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.liv.services.ComandaHartaService;
import ro.papetti.livrari.liv.services.ComandaPozService;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.model.TipCom;
import ro.papetti.livrari.plu.services.POrderCapService;
import ro.papetti.livrari.plu.services.POrderPozService;
import ro.papetti.livrari.plu.services.SOrderCapService;
import ro.papetti.livrari.plu.services.SOrderPozService;
import ro.papetti.livrari.utilitare.UtilComenzi;
import ro.papetti.pluriva.entity.POrderCap;
import ro.papetti.pluriva.entity.SOrderCap;
import ro.papetti.pluriva.mapper.UnitateMapper;

/**
 * @author MariusO
 */
@Service
@Transactional("livrariTransactionManager")
public class ComandaHartaServiceImpl implements ComandaHartaService {

    //    @Autowired
    private final ComandaCapService capService;
    private final ComandaPozService pozService;
    private final POrderCapService pOrderCapService;
    private final POrderPozService pOrderPozService;
    private final SOrderCapService sOrderCapService;
    private final SOrderPozService sOrderPozService;

    @Autowired
    private InfoMarfa infoMarfaBean;

    public ComandaHartaServiceImpl(ComandaCapService capService, ComandaPozService pozService,
                                   POrderCapService pOrderCapService, POrderPozService pOrderPozService,
                                   SOrderCapService sOrderCapService, SOrderPozService sOrderPozService) {
        this.capService = capService;
        this.pozService = pozService;
        this.pOrderCapService = pOrderCapService;
        this.pOrderPozService = pOrderPozService;
        this.sOrderCapService = sOrderCapService;
        this.sOrderPozService = sOrderPozService;
    }

    @Transactional
    public Optional<ComandaHarta> getComandaHartaById(int capId) {

//        ComandaCap comandaCap = capService.findById(capId).get();
        Optional<ComandaCap> comandaCap = capService.findByIdCuPozitii(capId);
        if (comandaCap.isEmpty()) {
            return Optional.empty();
        }

        ComandaHarta comandaHarta = new ComandaHarta(comandaCap.orElse(null));
        int firmaId = comandaHarta.getFirmaId();
        Set<StocDisponibil> stocuri = infoMarfaBean.getStocuriDisponibile(firmaId);

        if (comandaCap.get().getCom().equals(TipCom.FURNIZOR.name())) {
            POrderCap pOrderCap = pOrderCapService
                    .findByIdCuPozitiiSiLegaturaLaComenzi(comandaCap.get().getOrderCapId())
                    .orElse(null);

            if (pOrderCap != null) {
                Hibernate.initialize(pOrderCap.getFurnizorUnitate());
                comandaHarta.setPlata(pOrderCap.getPlata());
                comandaHarta.setUnitate(UnitateMapper.convert(pOrderCap.getFurnizorUnitate(), UnitateMapper::toDTO));
                comandaHarta.setPozPluFromPOrder(pOrderCap.getPozitii());
            }
            UtilComenzi.putStocuriDisponibile(comandaHarta, stocuri);
            return Optional.of(comandaHarta);
        }
        if (comandaCap.get().getCom().equals(TipCom.CLIENT.name())) {
            SOrderCap sOrderCap = sOrderCapService
                    .findByIdCuPozitiiSiLegaturaLaAprov(comandaCap.get().getOrderCapId())
                    .orElse(null);
            if (sOrderCap != null) {
                comandaHarta.setPlata(sOrderCap.getPlata());
                comandaHarta.setUnitate(UnitateMapper.convert(sOrderCap.getClient(), UnitateMapper::toDTO));
                comandaHarta.setPozPluFromSOrder(sOrderCap.getPozitii());
            }

            UtilComenzi.putStocuriDisponibile(comandaHarta, stocuri);
            UtilComenzi.putCantitatiLivrateS(comandaHarta, infoMarfaBean);
            UtilComenzi.putCantitatiRezervateS(comandaHarta, infoMarfaBean);
            return Optional.of(comandaHarta);

        }
        if (comandaCap.get().getCom().equals(TipCom.ACT_PRO.name())) {

            return Optional.of(comandaHarta);
        }
        if (comandaCap.get().getCom().equals(TipCom.OBI_FIX.name())) {

            return Optional.of(comandaHarta);
        } else {
            throw new RuntimeException("Nu e acceptat inca tipul de comanda: " + comandaCap.get().getCom());
        }

    }

}
