/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.liv.services.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.dto.ComandaCapDto;
import ro.papetti.LivrariTabele.dto.CoordonateFixeDto;
import ro.papetti.LivrariTabele.entity.ComandaCap;
import ro.papetti.LivrariTabele.entity.CoordonateFixe;
import ro.papetti.LivrariTabele.mapstruct.ComandaCapMapStruct;
import ro.papetti.LivrariTabele.mapstruct.CoordonateFixeMapStruct;
import ro.papetti.livrari.liv.services.ComandaCapService;
import ro.papetti.livrari.liv.services.ComandaHartaService;
import ro.papetti.livrari.liv.services.CoordonateFixeService;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.model.TipCom;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.FollowUpDto;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.SorderCapDto;

import java.util.Optional;

/**
 * @author MariusO
 */
@Slf4j
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

    private final ComandaCapMapStruct comandaCapMapStruct;
    private final CoordonateFixeMapStruct coordonateFixeMapStruct;


    @Override
    @Transactional
    public Optional<ComandaHarta> getComandaHartaById(int capId) {

        Optional<ComandaCap> optionalComandaCap = capService.findEagerById(capId);
        if (optionalComandaCap.isEmpty()) {
            log.info("Nu gasesc ComandaCap cu capId: "+capId);
            return Optional.empty();
        }
        ComandaCapDto comandaCapDto = comandaCapMapStruct.toDto(optionalComandaCap.get());
        int firmaId = comandaCapDto.getFirmaId();
//        List<StocDisponibil> stocuri = infoMarfaBean.getStocuriDisponibile(firmaId);

        if (optionalComandaCap.get().getCom().equals(TipCom.FURNIZOR.name())) {
            Optional<PorderCapDto> optionalPorderCapDto = porderCapService
                    .findDtoById(comandaCapDto.getOrderCapId());

            if (optionalPorderCapDto.isEmpty()) {
                log.info("Nu gasesc comanda de furnizor cu PorderCapId: " + comandaCapDto.getOrderCapId());
                return Optional.empty();
            }

            ComandaHarta comandaHarta = new ComandaHarta(optionalPorderCapDto.get(), comandaCapDto);
            return Optional.of(comandaHarta);
        }
        if (comandaCapDto.getCom().equals(TipCom.CLIENT.name())) {
            Optional<SorderCapDto> optionalSorderCapDto = sorderCapService
                    .findDtoById(comandaCapDto.getOrderCapId());
            if (optionalSorderCapDto.isEmpty()) {
                log.info("Nu gasesc comanda de client cu SorderCapId: " + comandaCapDto.getOrderCapId());
                return Optional.empty();
            }

            /* TODO de pus cantitati livrate */
            /* TODO de pus cantitati rezervate */
            ComandaHarta comandaHarta = new ComandaHarta(optionalSorderCapDto.get(), comandaCapDto);
            return Optional.of(comandaHarta);
        }
        if (comandaCapDto.getCom().equals(TipCom.ACT_PRO.name())) {
            Optional<FollowUpDto> optionalFollowUpDto = followUpService.findDtoById(comandaCapDto.getOrderCapId());
            if (optionalFollowUpDto.isEmpty()) {
                log.info("Nu gasesc FollowUp cu followUpIdId:" + comandaCapDto.getOrderCapId());
                return Optional.empty();
            }
            ComandaHarta comandaHarta = new ComandaHarta(optionalFollowUpDto.get(),comandaCapDto);
            return Optional.of(comandaHarta);

        }
        if (optionalComandaCap.get().getCom().equals(TipCom.OBI_FIX.name())) {
            Optional<CoordonateFixe> optionalCoordonateFixe=
                    coordonateFixeService.findById(optionalComandaCap.get().getOrderCapId());
            if (optionalCoordonateFixe.isEmpty()){
                log.info("Nu gasesc CoordonateFixe cu idCoordonata: " + comandaCapDto.getOrderCapId());
                return Optional.empty();
            }
            CoordonateFixeDto coordonateFixeDto = coordonateFixeMapStruct.toDto(optionalCoordonateFixe.get());
            ComandaHarta comandaHarta = new ComandaHarta(coordonateFixeDto, comandaCapDto);
            return Optional.of(comandaHarta);
        } else {
            log.error(("Nu e acceptat inca tipul de comanda: " + optionalComandaCap.get().getCom()));
            throw new RuntimeException("Nu e acceptat inca tipul de comanda: " + comandaCapDto.getCom());
        }

    }

}
