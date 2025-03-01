/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.CoordonateFixe;
import ro.papetti.componente.InfoMarfa;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.liv.services.ComandaHartaService;
import ro.papetti.livrari.liv.services.CoordonateFixeService;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.model.TipCom;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.FollowUpDto;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderCapDto;

import java.util.Optional;
import java.util.Set;

/**
 * @author MariusO
 */
@RequiredArgsConstructor
@Service
@Transactional("livrariTransactionManager")
public class ComandaHartaServiceImpl implements ComandaHartaService {

    //    @Autowired
    private final ComandaCapService capService;
    private final PorderCapService porderCapService;
    private final SorderCapService sorderCapService;
    private final FollowUpService followUpService;
    private final CoordonateFixeService coordonateFixeService;
    private final InfoMarfa infoMarfaBean;


    @Transactional
    @Override
    public Optional<ComandaHarta> getComandaHartaById(int capId) {

        Optional<ComandaCap> optionalComandaCap = capService.findEagerById(capId);
        if (optionalComandaCap.isEmpty()) {
            System.out.println("Nu gasesc ComandaCap cu capId: "+capId);
            return Optional.empty();
        }

        int firmaId = optionalComandaCap.get().getFirmaId();
        Set<StocDisponibil> stocuri = infoMarfaBean.getStocuriDisponibile(firmaId);

        if (optionalComandaCap.get().getCom().equals(TipCom.FURNIZOR.name())) {
            Optional<PorderCapDto> optionalPorderCapDto = porderCapService
                    .findDtoById(optionalComandaCap.get().getOrderCapId());

            if (optionalPorderCapDto.isEmpty()) {
                System.out.println("Nu gasesc comanda de furnizor cu PorderCapId: " + optionalComandaCap.get().getOrderCapId());
                return Optional.empty();
            }

            ComandaHarta comandaHarta = new ComandaHarta(optionalPorderCapDto.get(), optionalComandaCap.get());
            return Optional.of(comandaHarta);
            /* TODO de pus stocurile */
        }
        if (optionalComandaCap.get().getCom().equals(TipCom.CLIENT.name())) {
            Optional<SorderCapDto> optionalSorderCap = sorderCapService
                    .findDtoById(optionalComandaCap.get().getOrderCapId());
            if (optionalSorderCap.isEmpty()) {
                System.out.println("Nu gasesc comanda de client cu SorderCapId: " + optionalComandaCap.get().getOrderCapId());
                return Optional.empty();
            }
            /* TODO de pus stocuri */
            /* TODO de pus cantitati livrate */
            /* TODO de pus cantitati rezervate */
//            UtilComenzi.putStocuriDisponibile(comandaHarta, stocuri);
//            UtilComenzi.putCantitatiLivrateS(comandaHarta, infoMarfaBean);
//            UtilComenzi.putCantitatiRezervateS(comandaHarta, infoMarfaBean);
            ComandaHarta comandaHarta = new ComandaHarta(optionalSorderCap.get(), optionalComandaCap.get());
            return Optional.of(comandaHarta);
        }
        if (optionalComandaCap.get().getCom().equals(TipCom.ACT_PRO.name())) {
            Optional<FollowUpDto> optionalFollowUpDto = followUpService.findDtoById(optionalComandaCap.get().getOrderCapId());
            if (optionalFollowUpDto.isEmpty()) {
                System.out.println("Nu gasesc FollowUp cu followUpIdId:" + optionalComandaCap.get().getOrderCapId());
                return Optional.empty();
            }
            ComandaHarta comandaHarta = new ComandaHarta(optionalFollowUpDto.get(), optionalComandaCap.get());
            return Optional.of(comandaHarta);

        }
        if (optionalComandaCap.get().getCom().equals(TipCom.OBI_FIX.name())) {
            Optional<CoordonateFixe> optionalCoordonateFixe=
                    coordonateFixeService.findById(optionalComandaCap.get().getOrderCapId());
            if (optionalCoordonateFixe.isEmpty()){
                System.out.println("Nu gasesc CoordonateFixe cu idCoordonata: " + optionalComandaCap.get().getOrderCapId());
                return Optional.empty();
            }
            ComandaHarta comandaHarta = new ComandaHarta(optionalCoordonateFixe.get(),optionalComandaCap.get());
            return Optional.of(comandaHarta);
        } else {
            throw new RuntimeException("Nu e acceptat inca tipul de comanda: " + optionalComandaCap.get().getCom());
        }

    }

}
