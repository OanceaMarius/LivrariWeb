/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.componente.InfoMarfa;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.liv.services.ComandaHartaService;
import ro.papetti.livrari.liv.services.ComandaPozService;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.model.TipCom;
import ro.papetti.livrari.plu.services.PorderCapService;
import ro.papetti.livrari.plu.services.PorderPozService;
import ro.papetti.livrari.plu.services.SorderCapService;
import ro.papetti.livrari.plu.services.SorderPozService;
import ro.papetti.livrari.utilitare.UtilComenzi;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.SorderCap;

import java.util.Optional;
import java.util.Set;

/**
 * @author MariusO
 */
@Service
@Transactional("livrariTransactionManager")
public class ComandaHartaServiceImpl implements ComandaHartaService {

    //    @Autowired
    private final ComandaCapService capService;
    private final ComandaPozService pozService;
    private final PorderCapService porderCapService;
    private final PorderPozService porderPozService;
    private final SorderCapService sorderCapService;
    private final SorderPozService sorderPozService;

    @Autowired
    private InfoMarfa infoMarfaBean;

    public ComandaHartaServiceImpl(ComandaCapService capService, ComandaPozService pozService,
                                   PorderCapService pOrderCapService, PorderPozService pOrderPozService,
                                   SorderCapService sOrderCapService, SorderPozService sOrderPozService) {
        this.capService = capService;
        this.pozService = pozService;
        this.porderCapService = pOrderCapService;
        this.porderPozService = pOrderPozService;
        this.sorderCapService = sOrderCapService;
        this.sorderPozService = sOrderPozService;
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
            PorderCap pOrderCap = porderCapService
                    .findByIdCuPozitiiSiLegaturaLaComenzi(comandaCap.get().getOrderCapId())
                    .orElse(null);

            if (pOrderCap != null) {
                Hibernate.initialize(pOrderCap.getFurnizorUnitate());
                comandaHarta.setPozPluFromPOrder(pOrderCap.getPozitii());
            }
            UtilComenzi.putStocuriDisponibile(comandaHarta, stocuri);
            return Optional.of(comandaHarta);
        }
        if (comandaCap.get().getCom().equals(TipCom.CLIENT.name())) {
            Optional<SorderCap> sOrderCap = sorderCapService
                    .findByIdCuPozitiiSiLegaturaLaAprov(comandaCap.get().getCapId());
            if (sOrderCap.isPresent()) {
                comandaHarta.setPozPluFromSOrder(sOrderCap.get().getPozitii());
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
