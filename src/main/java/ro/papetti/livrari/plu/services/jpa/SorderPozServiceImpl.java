/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.SorderPozRepozitory;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;
import ro.papetti.pluriva.dtoi.SorderPozDTOI;
import ro.papetti.pluriva.entity.SorderCap;
import ro.papetti.pluriva.entity.SorderPoz;
import ro.papetti.pluriva.mapstruct.SorderCapMapStruct;
import ro.papetti.pluriva.mapstruct.SorderPozMapStruct;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class SorderPozServiceImpl extends BaseServiceImpl<SorderPoz, SorderPozRepozitory> implements SorderPozService {

    public SorderPozServiceImpl(SorderPozRepozitory repozitory) {
        super(repozitory);
    }

    private CompletareDtoService completareDtoService;

    @Autowired
    private ProdusService produsService;
    @Autowired
    private SorderCapMapStruct sorderCapMapStruct;
    @Autowired
    private SorderPozMapStruct sorderPozMapStruct;
    @Autowired
    private UnitateService unitateService;


    @Override
    public List<SorderPozDTOI> findPozitiiDTOBySOrderCapId(int sOrderCapId) {
        return rep.findPozDTOIBySorderCapId(sOrderCapId, SorderPozDTOI.class);
    }

    @Override
    public List<SorderPozDTOI> findPozitiiDTOBySOrderCapIdCUProduse(int sOrderCapId) {
        List<SorderPozDTOI> listPoz = findPozitiiDTOBySOrderCapId(sOrderCapId);
        if (listPoz != null) {
            for (SorderPozDTOI poz : listPoz) {
                Hibernate.initialize(poz.getProdus());
            }
        }
        return listPoz;
    }


    public List<SorderPozDTOI> findPozitiiDTOBySOrderCapIdCuProduseSiFurnizori(int sOrderCapId) {
        List<SorderPozDTOI> listPoz = findPozitiiDTOBySOrderCapIdCUProduse(sOrderCapId);
        if (listPoz != null) {
            for (SorderPozDTOI poz : listPoz) {
                Hibernate.initialize(poz.getPorderPoz());
                var pPoz = poz.getPorderPoz();
                if (pPoz != null) {
                    Hibernate.initialize(poz.getPorderPoz().getpOrderCap());
                    Hibernate.initialize(poz.getPorderPoz().getpOrderCap().getFurnizorUnitate());
                }

            }
        }
        return listPoz;
    }

    @Override
    public List<SorderPoz> findPozitiiBySOrderCapId(int sOrderCapId) {
        return rep.findPozDTOIBySorderCapId(sOrderCapId, SorderPoz.class);
    }


    @Override
    public Optional<SorderCapDto> findSorderCapDtoBySorderPozId(int sorderPozId){
        Optional<SorderCap> sorderCap = rep.findSorderCapBySorderPozId(sorderPozId);
        Optional<SorderCapDto> sorderCapDto = sorderCap.map(value->sorderCapMapStruct.toDto(value));
        if (sorderCapDto.isPresent())
        {
            setSorderCapDtoByCache(sorderCapDto.get());
        }
        return sorderCapDto;

    }

    @Override
    public Optional<SorderPoz> findEagerById(@NonNull int sorderPozId){
        return rep.findEagerById(sorderPozId);
    }


    @Override
    public List<SorderPoz> findEagerBySorderCapId(@NonNull int sorderCapId){
        return rep.findEagerBySorderCapId(sorderCapId);
    }

    @Override
    public List<SorderPozDto> findPozDtoBySOrderCapId(int sorderCapId) {
        List<SorderPozDto> sorderPozDtoList = sorderPozMapStruct.toDtoList(rep.findBySorderCapId(sorderCapId));
        for (SorderPozDto pozDto:sorderPozDtoList){
            if (produsService.findDtoById(pozDto.getProdusId()).isPresent())
                pozDto.setProdusDto(produsService.findDtoById(pozDto.getProdusId()).get());
        }
        return sorderPozDtoList;
    }
    private void setSorderCapDtoByCache(SorderCapDto sorderCapDto){

            sorderCapDto.setClientUnitateDto(unitateService.findDtoById(sorderCapDto.getClientSintezaId()).orElse(null));
            sorderCapDto.setUserIntroducereDto(completareDtoService.getUserDtoById(sorderCapDto.getUserIntroducereId()));
            sorderCapDto.setStareDocDto(completareDtoService.getStareDocDtoById(sorderCapDto.getStareId()));
            sorderCapDto.setTipLivrareDto(completareDtoService.getTipLivrareById(sorderCapDto.getTipLivrareId()));
            sorderCapDto.setModPlataDto(completareDtoService.getModPlataById(sorderCapDto.getModPlataId()));
            sorderCapDto.setTermenPlataDto(completareDtoService.getTermenPlataById(sorderCapDto.getTermenPlataId()));
            sorderCapDto.setTipDocDto(completareDtoService.getTipDocById(sorderCapDto.getTipDocId()));

    }


}
