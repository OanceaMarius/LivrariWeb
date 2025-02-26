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
import ro.papetti.livrari.plu.services.PorderPozService;
import ro.papetti.livrari.plu.services.ProdusService;
import ro.papetti.pluriva.dto.PorderPozDto;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.mapstruct.PorderPozMapStruct;

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
    private ProdusService produsService;

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
    public List<PorderPozDto> findPozDtoByPOrderCapId(int pOrderCapId) {
        List<PorderPozDto> porderPozDtoList = porderPozMapStruct.toDtoList(rep.findByPorderCapId(pOrderCapId));
        for (PorderPozDto pozDto:porderPozDtoList){
            if (produsService.findDtoById(pozDto.getProdusId()).isPresent())
                pozDto.setProdusDto(produsService.findDtoById(pozDto.getProdusId()).get());
        }
        return porderPozDtoList;
    }
}
