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
    @Autowired
    private UnitateService unitateService;
    @Autowired
    private ProdusService produsService;
    @Autowired
    private SorderCapMapStruct sorderCapMapStruct;
    @Autowired
    private SorderPozMapStruct sorderPozMapStruct;
    @Autowired
    private UserService userService;
    @Autowired
    private StareDocService stareDocService;
    @Autowired
    private TipLivrareService tipLivrareService;
    @Autowired
    private ModPlataService  modPlataService;
    @Autowired
    private TermenPlataService termenPlataService;
    @Autowired
    private TipDocService tipDocService;


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
            setSorderCapDtoByCache(sorderCapDto);
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
    private void setSorderCapDtoByCache(Optional<SorderCapDto> sorderCapDto){
        if (sorderCapDto.isPresent()){
            setDtoClientSinteza(sorderCapDto.get());
            setDtoUserIntroducere(sorderCapDto.get());
            setDtoStareDoc(sorderCapDto.get());
            setDtoTipLivrare(sorderCapDto.get());
            setDtoModPlata(sorderCapDto.get());
            setDtoTermenPlata(sorderCapDto.get());
            setDtoTipDoc(sorderCapDto.get());
        }


    }

    private void setDtoClientSinteza(SorderCapDto sorderCapDto){
        int clientSintezaId = sorderCapDto.getClientSintezaId();
        Optional<UnitateDto> clientSintezaDto = unitateService.findDtoById(clientSintezaId);
        if (clientSintezaDto.isPresent()){
            sorderCapDto.setClientLivrareUnitateDto(clientSintezaDto.get());
        }
    }

    private void setDtoUserIntroducere(SorderCapDto sorderCapDto){
        Integer userIntroducereId = sorderCapDto.getUserIntroducereId();
        if (userIntroducereId==null)
            return;

        Optional<UserDto> userIntroducere = userService.findDtoById(userIntroducereId);
        if (userIntroducere.isPresent()){
            sorderCapDto.setUserIntroducereDto(userIntroducere.get());
        }
    }

    private void setDtoStareDoc(SorderCapDto sorderCapDto){
        Integer stareDocId = sorderCapDto.getStareId();
        if (stareDocId==null)
            return;

        Optional<StareDocDto> stareDocDto = stareDocService.findDtoById(stareDocId);
        if (stareDocDto.isPresent())
            sorderCapDto.setStareDocDto(stareDocDto.get());

    }

    private void setDtoTipLivrare(SorderCapDto sorderCapDto){
        Integer tipLivrareId = sorderCapDto.getTipLivrareId();
        if (tipLivrareId==null)
            return;

        Optional<TipLivrareDto>tipLivrareDto =tipLivrareService.findDtoById(tipLivrareId);
        if (tipLivrareDto.isPresent())
            sorderCapDto.setTipLivrareDto(tipLivrareDto.get());
    }

    private void setDtoModPlata(SorderCapDto sorderCapDto){
        Integer modPlataId = sorderCapDto.getModPlataId();
        if (modPlataId==null)
            return;

        Optional<ModPlataDto>modPlataDto = modPlataService.findDtoById(modPlataId);
        if (modPlataDto.isPresent())
            sorderCapDto.setModPlataDto(modPlataDto.get());
    }

    private void setDtoTermenPlata(SorderCapDto sorderCapDto){
        Integer termenPlataId = sorderCapDto.getTermenPlataId();
        if (termenPlataId==null)
            return;
        Optional<TermenPlataDto> termenPlataDto= termenPlataService.findDtoById(termenPlataId);
        if (termenPlataDto.isPresent())
            sorderCapDto.setTermenPlataDto(termenPlataDto.get());

    }

    private void setDtoTipDoc(SorderCapDto sorderCapDto){
        Integer tipDocId = sorderCapDto.getTipDocId();
        if (tipDocId==null)
            return;

        Optional<TipDocDto> tipDocDto = tipDocService.findDtoById(tipDocId);
        if (tipDocDto.isPresent())
            sorderCapDto.setTipDocDto(tipDocDto.get());
    }



}
