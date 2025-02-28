/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.PorderPozRepozitory;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.PorderPozDto;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.entity.SorderCap;
import ro.papetti.pluriva.mapstruct.PorderPozMapStruct;
import ro.papetti.pluriva.mapstruct.SorderCapMapStruct;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class PorderPozServiceImpl extends BaseServiceImpl<PorderPoz, PorderPozRepozitory> implements PorderPozService {



    public PorderPozServiceImpl(PorderPozRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private PorderPozMapStruct porderPozMapStruct;
    @Autowired
    private SorderCapMapStruct sorderCapMapStruct;
    @Autowired
    private ProdusService produsService;
    @Autowired
    private UnitateService unitateService;
    @Autowired
    private CompletareDtoService completareDtoService;

    @Override
    public List<PorderPoz> findEagerByPorderCapId(int pOrderCapId){
        return rep.findEagerByPorderCapId(pOrderCapId);
    }

    @Override
    public Optional<PorderPoz> findEagerById(int porderPozId){
        return rep.findEagerById(porderPozId);
    }

    @Override
    public <T> List<T> findPozDTOIByPOrderCapId(int pOrderCapId, Class<T> type) {
        return rep.findDTOIByPorderCapId(pOrderCapId,type);
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
    public List<PorderPozDto> findPozDtoByPOrderCapId(int pOrderCapId) {
        List<PorderPozDto> porderPozDtoList = porderPozMapStruct.toDtoList(rep.findByPorderCapId(pOrderCapId));
        for (PorderPozDto pozDto:porderPozDtoList){
            if (produsService.findDtoById(pozDto.getProdusId()).isPresent())
                pozDto.setProdusDto(produsService.findDtoById(pozDto.getProdusId()).get());
            if (pozDto.getSorderPozId()!=null)
                pozDto.setSorderCapDto(findSorderCapFaraPozitiiDtoBySorderPozId(pozDto.getSorderPozId()).orElse(null));
        }
        return porderPozDtoList;
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
