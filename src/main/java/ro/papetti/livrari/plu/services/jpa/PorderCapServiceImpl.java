/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.PorderCapRepozitory;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dtoi.PorderCapDTOI;
import ro.papetti.pluriva.entity.PorderCap;
import ro.papetti.pluriva.entity.PorderPoz;
import ro.papetti.pluriva.mapstruct.PorderCapMapStruct;

/**
 *
 * @author MariusO
 */
@Service
@Transactional("plurivaTransactionManager")
public class PorderCapServiceImpl extends BaseServiceImpl<PorderCap, PorderCapRepozitory> implements PorderCapService {



    public PorderCapServiceImpl(PorderCapRepozitory repozitory) {
        super(repozitory);
    }

    public Optional<PorderCap> findByPOrderCapId(int pOrderCapId) {
        return rep.findById(pOrderCapId);

    }
    @Autowired
    private PorderCapMapStruct porderCapMapStruct;
    @Autowired
    private PorderPozService porderPozService;
    @Autowired
    private CompletareDtoService completareDtoService;
    @Autowired
    private UnitateService unitateService;


    @Override
    public List<PorderPoz> findPOrderPozByPOrderCapId(int pOrderCapId) {
        PorderCap cap = rep.findById(pOrderCapId)
                .orElseThrow(() -> new EntityNotFoundException("Nu gasesc POrderCap cu id: " + pOrderCapId));
        Hibernate.initialize(cap.getPozitii());
        return cap.getPozitii();
    }
/**
 * Aduce doar Capul fara pozitii
 * @param pOrderCapId
 * @return 
 */
    @Override
    public Optional<PorderCap> findById(Integer pOrderCapId) {

        Optional<PorderCap> pCap = rep.findById(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getUserIntroducere());
            Hibernate.initialize(pCap.get().getPozitii());
            Hibernate.initialize(pCap.get().getFurnizorUnitate());
        }
        return pCap;
    }

    /**
     * Aduce si datele despre partener
     * @param pOrderCapId
     * @return
     */
    public Optional<PorderCap> findByIdCuFurnizor(Integer pOrderCapId) {
        Optional<PorderCap> pCap = findById(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getFurnizorUnitate());
        }
        return pCap;
    }




    @Override
    public List<PorderCap> findByDataLivrare(Date dataLivrare) {
        List<PorderCap> listPcap = rep.findByDataLivrare(dataLivrare);
        if (!listPcap.isEmpty()){
            for (PorderCap pOrderCap:listPcap){
                Hibernate.initialize(pOrderCap.getFurnizorUnitate());
                Hibernate.initialize(pOrderCap.getPozitii());
                Hibernate.initialize(pOrderCap.getUserIntroducere());
                Hibernate.initialize(pOrderCap.getFurnizorUnitate().getUserIntroducere());
                Hibernate.initialize(pOrderCap.getFurnizorUnitate().getUserModificare());
            }
        }
        return listPcap;
    }

    @Override
    public Optional<PorderCapDTOI> findDTOIByPOrderCapId(Integer pOrderCapId) {
        Optional<PorderCapDTOI> pCap = rep.findDTOByPOrderCapId(pOrderCapId);
        if (pCap.isPresent()) {
            Hibernate.initialize(pCap.get().getPozitii());
            Hibernate.initialize(pCap.get().getFurnizorUnitate());
        }
        return pCap;
    }

    @Override
    public Optional<PorderCap> findEagerById(int porderCapId){
        return rep.findEagerById(porderCapId);
    }


    @Override
    public Optional<PorderCapDto> findDtoById(int porderCapId){
        Optional<PorderCap>porderCap=rep.findById(porderCapId);
        Optional<PorderCapDto>porderCapDto = porderCap.map(value->porderCapMapStruct.toDto(value));
        if (porderCapDto.isEmpty())
            return Optional.empty();

        setPorderCapDtoCuPozitiiFromCache(porderCapDto.get());
        return porderCapDto;
    }


    private void setPorderCapDtoCuPozitiiFromCache(PorderCapDto porderCapDto) {
        setPorderCapDtoFaraPozitiiFromCache(porderCapDto);
        porderCapDto.setPozitiiDto(porderPozService.findPozDtoByPOrderCapId(porderCapDto.getPorderCapId()));

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
