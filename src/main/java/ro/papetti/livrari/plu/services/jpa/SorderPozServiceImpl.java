/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.SorderPozRepozitory;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;
import ro.papetti.pluriva.dtoi.SorderPozDTOI;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.SorderPoz;
import ro.papetti.pluriva.mapstruct.PorderCapMapStruct;
import ro.papetti.pluriva.mapstruct.SorderPozMapStruct;

import java.util.ArrayList;
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
    private CompletareDtoService completareDtoService;
    @Autowired
    private ProdusService produsService;
    @Autowired
    private SorderPozMapStruct sorderPozMapStruct;
    @Autowired
    private PorderCapMapStruct porderCapMapStruct;
    @Autowired
    private UnitateService unitateService;


    @Override
    public List<SorderPozDTOI> findPozitiiDTOIBySOrderCapId(int sOrderCapId) {
        return rep.findPozDTOIBySorderCapId(sOrderCapId, SorderPozDTOI.class);
    }


    @Override
    public List<SorderPoz> findPozitiiBySOrderCapId(int sOrderCapId) {
        return rep.findPozDTOIBySorderCapId(sOrderCapId, SorderPoz.class);
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

            List<Integer> porderPozIdList = rep.findPorderPozIdBySorderPozId(pozDto.getSorderPozId());
            pozDto.setPorderPozIdList(porderPozIdList);
            List<PorderCapDto> porderCapDtoList = new ArrayList<>(porderPozIdList.size());
            for (int porderPozId: porderPozIdList){
                porderCapDtoList.add(findPorderCapDtoFaraPozitiiByPorderPozId(porderPozId).orElse(null));
            }
            pozDto.setPorderCapDtoList(porderCapDtoList);
        }
        return sorderPozDtoList;
    }

    @Override
    public Optional<PorderCapDto> findPorderCapDtoFaraPozitiiByPorderPozId(int porderPozId){
        Optional<PorderCap>optionalPorderCap= rep.findPorderCapByPorderPozId(porderPozId);

        Optional<PorderCapDto> porderCapDto = optionalPorderCap.map(value->porderCapMapStruct.toDto(value));
        if (porderCapDto.isPresent())
            setPorderCapDtoFaraPozitiiFromCache(porderCapDto.get());;
        return porderCapDto;
    }


    private void setPorderCapDtoFaraPozitiiFromCache(PorderCapDto porderCapDto) {
        porderCapDto.setFurnizorUnitateDto(unitateService.findDtoById(porderCapDto.getFurnizorId()).orElse(null));
        porderCapDto.setUserIntroducereDto(completareDtoService.getUserDtoById(porderCapDto.getUserIntroducereId()));
        porderCapDto.setStareDocDto(completareDtoService.getStareDocDtoById(porderCapDto.getStareId()));
        porderCapDto.setTipLivrareDto(completareDtoService.getTipLivrareDtoById(porderCapDto.getTipLivrareId()));
        porderCapDto.setModPlataDto(completareDtoService.getModPlataDtoById(porderCapDto.getModPlataId()));
        porderCapDto.setTermenPlataDto(completareDtoService.getTermenPlataDtoById(porderCapDto.getTermenPlataId()));
        porderCapDto.setTipDocDto(completareDtoService.getTipDocDtoById(porderCapDto.getTipDocId()));

    }

}
