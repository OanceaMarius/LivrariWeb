/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import java.util.Set;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ro.papetti.Componente.InfoMarfa;
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

/**
 *
 * @author MariusO
 */
@Service
@Transactional("livrariTransactionManager")
public class ComandaHartaServiceImpl implements ComandaHartaService{

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
    public ComandaHarta getComandaHartaById(int capId) {

//        ComandaCap comandaCap = capService.findById(capId).get();
        ComandaCap comandaCap = capService.findByIdCuPozitii(capId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "ComandaCap  cu CapId: " + capId + " nu a fost găsită"));

        ComandaHarta comandaHarta = new ComandaHarta(comandaCap);
        int firmaId = comandaHarta.getFirmaId();
        Set<StocDisponibil> stocuri = infoMarfaBean.getStocuriDisponibile(firmaId);

        if (comandaCap.getCom().equals(TipCom.FURNIZOR.name())) {
            POrderCap pOrderCap = pOrderCapService
                    .findByIdCuPozitiiSiLegaturaLaComenzi(comandaCap.getOrderCapId())
                    .orElse(null);

            Hibernate.initialize(pOrderCap.getFurnizorUnitate());
            if (pOrderCap != null) {
                comandaHarta.setPlata(pOrderCap.getPlata());
                comandaHarta.setUnitate(pOrderCap.getFurnizorUnitate());
                comandaHarta.setPozPluFromPOrder(pOrderCap.getPozitii());
            }
            UtilComenzi.putStocuriDisponibile(comandaHarta, stocuri);
            return comandaHarta;
        }
        if (comandaCap.getCom().equals(TipCom.CLIENT.name())) {
            SOrderCap sOrderCap = sOrderCapService
                    .findByIdCuPozitiiSiLegaturaLaAprov(comandaCap.getOrderCapId())
                    .orElse(null);
            if (sOrderCap != null) {
                comandaHarta.setPlata(sOrderCap.getPlata());
                comandaHarta.setUnitate(sOrderCap.getClient());
                comandaHarta.setPozPluFromSOrder(sOrderCap.getPozitii());
            }

            UtilComenzi.putStocuriDisponibile(comandaHarta, stocuri);
            UtilComenzi.putCantitatiLivrateS(comandaHarta, infoMarfaBean);
            UtilComenzi.putCantitatiRezervateS(comandaHarta, infoMarfaBean);
            return comandaHarta;

        }
        if (comandaCap.getCom().equals(TipCom.ACT_PRO.name())) {

            return comandaHarta;
        }
        if (comandaCap.getCom().equals(TipCom.OBI_FIX.name())) {

            return comandaHarta;
        } else {
            throw new RuntimeException("Nu e acceptat inca tipul de comanda: " + comandaCap.getCom());
        }

    }

}
