/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.plu.repozitories.SorderCapRepozitory;
import ro.papetti.livrari.plu.services.CompletareDtoService;
import ro.papetti.livrari.plu.services.SorderCapService;
import ro.papetti.livrari.plu.services.SorderPozService;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.dtoi.SorderCapDTOI;
import ro.papetti.pluriva.dtoi.SorderPozDTOIFaraSorderCap;
import ro.papetti.pluriva.entity.SorderCap;
import ro.papetti.pluriva.entity.Unitate;
import ro.papetti.pluriva.mapstruct.SorderCapMapStruct;


/**
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SorderCapServiceImpl extends BaseServiceImpl<SorderCap, SorderCapRepozitory> implements SorderCapService {

    public SorderCapServiceImpl(SorderCapRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private SorderCapMapStruct sorderCapMapStruct;
    @Autowired
    private CompletareDtoService completareDtoService;
    @Autowired
    private UnitateService unitateService;
    @Autowired
    private SorderPozService sorderPozService;

    /**
     * @param sOrderCapId
     * @param firmaId
     * @return Cantitatile livrate la fiecare pozitie de comanda in pluriva
     */
    @Override
    public List<PozCantitate> getCantitatiLivrate(int sOrderCapId, int firmaId) {
        return rep.getCantitatiLivrate(sOrderCapId, firmaId);
    }

    @Override
    public Optional<List<SorderCap>> findByDataLivrare(Date dataLivrare) {
        return rep.findByDataLivrare(dataLivrare);
    }

    @Override
    public Optional<SorderCap> findEagerById(int sorderCapId) {
        return rep.findEagerById(sorderCapId);
    }

    @Override
    public Optional<SorderCapDto> findDtoById(int sorderCapId) {
        Optional<SorderCap> sorderCap = rep.findById(sorderCapId);
        Optional<SorderCapDto> sorderCapDto = sorderCap.map(value -> sorderCapMapStruct.toDto(value));
        sorderCapDto.ifPresent(this::setDtoCuPozitiiFromCache);
        return sorderCapDto;
    }


    @Override
    public Optional<SorderCapDto> findSorderCapFaraPozitiiDtoBySorderPozId(int sorderPozId){
        Optional<SorderCap> sorderCap = rep.findSorderCapBySorderPozId(sorderPozId);
        Optional<SorderCapDto> sorderCapDto = sorderCap.map(value->sorderCapMapStruct.toDto(value));
        if (sorderCapDto.isPresent())
        {
            setSorderCapDtoFaraPozitiiFromCache(sorderCapDto.get());
        }
        return sorderCapDto;

    }


    @Override
    public Optional<SorderCap> findById(int sOrderCapId) {
        Optional<SorderCap> sCap = rep.findById(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getUserIntroducere());
        }
        return sCap;
    }


    @Override
    public Optional<SorderCap> findByIdCuClient(int sorderCapId) {
        Optional<SorderCap> sCap = findById(sorderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(getClient(sCap.get()));
        }
        return sCap;
    }



    @Override
    public List<PozCantitate> getCantitatiRezervate(int sOrderCapId) {
        return rep.getCantitatiRezervate(sOrderCapId);
    }

    @Override
    public Optional<SorderCapDTOI> findDTOIById(int sOrderCapId) {
        Optional<SorderCapDTOI> sCap = rep.findDTOIBySorderCapId(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getUserIntroducere());
        }
        return sCap;
    }

    @Override
    public Optional<SorderCapDTOI> findDTOIByIdCuClient(int sOrderCapId) {
        Optional<SorderCapDTOI> sCap = findDTOIById(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getClientUnitate());
            Hibernate.initialize(sCap.get().getClientLivrareUnitate());

        }
        return sCap;
    }

    @Override
    public Optional<SorderCapDTOI> findDTOIByIdCuPozitii(int sOrderCapId) {
        Optional<SorderCapDTOI> sCap = findDTOIByIdCuClient(sOrderCapId);
        if (sCap.isPresent()) {
            Hibernate.initialize(sCap.get().getPozitii());
            List<SorderPozDTOIFaraSorderCap> listSpoz = sCap.get().getPozitii();
            for (SorderPozDTOIFaraSorderCap poz : listSpoz) {
                Hibernate.initialize(poz.getProdus());
            }
        }
        return sCap;
    }


    /**
     * @param sorderCap
     * @return Clientul cu livrare daca exista ori clientul de facturare
     */
    private Unitate getClient(SorderCap sorderCap) {
        if (sorderCap.getClientLivrareUnitate() != null)
            return sorderCap.getClientLivrareUnitate();

        return sorderCap.getClientUnitate();
    }

    private void setDtoCuPozitiiFromCache(SorderCapDto sorderCapDto) {
        setSorderCapDtoFaraPozitiiFromCache(sorderCapDto);
        sorderCapDto.setPozitiiDto(sorderPozService.findPozDtoBySOrderCapId(sorderCapDto.getSorderCapId()));

    }

    private void setSorderCapDtoFaraPozitiiFromCache(SorderCapDto sorderCapDto) {
        sorderCapDto.setClientUnitateDto(unitateService.findDtoById(sorderCapDto.getClientId()).orElse(null));
        sorderCapDto.setClientLivrareUnitateDto(unitateService.findDtoById(sorderCapDto.getClientLivrareId()).orElse(null));
        sorderCapDto.setUserIntroducereDto(completareDtoService.getUserDtoById(sorderCapDto.getUserIntroducereId()));
        sorderCapDto.setStareDocDto(completareDtoService.getStareDocDtoById(sorderCapDto.getStareId()));
        sorderCapDto.setTipLivrareDto(completareDtoService.getTipLivrareDtoById(sorderCapDto.getTipLivrareId()));
        sorderCapDto.setModPlataDto(completareDtoService.getModPlataDtoById(sorderCapDto.getModPlataId()));
        sorderCapDto.setTermenPlataDto(completareDtoService.getTermenPlataDtoById(sorderCapDto.getTermenPlataId()));
        sorderCapDto.setTipDocDto(completareDtoService.getTipDocDtoById(sorderCapDto.getTipDocId()));

    }
}
