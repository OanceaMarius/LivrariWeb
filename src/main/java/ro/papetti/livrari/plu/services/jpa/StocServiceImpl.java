/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.plu.services.jpa;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.repozitories.StocRepozitory;
import ro.papetti.livrari.plu.services.PorderCapService;
import ro.papetti.livrari.plu.services.StocService;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.PorderPozDto;
import ro.papetti.pluriva.entity.FurnizorProdus;

/**
 *
 * @author MariusO
 */
@Service
@Transactional(value = "plurivaTransactionManager", readOnly = true)
@RequiredArgsConstructor
public class StocServiceImpl implements StocService{

    private final StocRepozitory stocRepozitory;
    private final PorderCapService porderCapService;
    
    @Override
    public List<StocDisponibil> getStocDisponibilInGestiune(int FirmaId, int GestiuneId){
        return stocRepozitory.getStocDisponibilInGestiune(FirmaId, GestiuneId);
    }

    @Override
    public List<StocDisponibil> getStocDisponibilInGestiuneOperationala(int FirmaId){
        int gestiuneOperationalaId = stocRepozitory.getGestiuneOperationalaPeFirma(FirmaId);
        return stocRepozitory.getStocDisponibilInGestiune(FirmaId,gestiuneOperationalaId);
    }

    @Override
    public int getGestiuneOperationalaPeFirma(int firmaId){
        return stocRepozitory.getGestiuneOperationalaPeFirma(firmaId);
    }


    @Override
    public Map<Integer, BigDecimal> getStocDisponibilInGestiuneFiltrat(int firmaId, int gestiuneId, List<Integer> produsIdList){

        List<StocDisponibil> stocDisponibilList = stocRepozitory.getStocDisponibilInGestiuneFiltrat(firmaId,gestiuneId,produsIdList);

        Map<Integer, BigDecimal> stocDisponibilMap = new HashMap<>();
        for (StocDisponibil sd: stocDisponibilList){
            stocDisponibilMap.put(sd.getProdusId(),sd.getStocDisponibil());
        }
        return  stocDisponibilMap;
    }


    @Override
    public Map<Integer, BigDecimal> getStocDisponibilInGestiuneOperationalaFiltrat(int firmaId, List<Integer> produsIdList){

        int gestiuneOperationalaId = getGestiuneOperationalaPeFirma(firmaId);
        List<StocDisponibil> stocDisponibilList = stocRepozitory.getStocDisponibilInGestiuneFiltrat(firmaId, gestiuneOperationalaId, produsIdList);

        Map<Integer, BigDecimal> stocDisponibilMap = new HashMap<>();
        for (StocDisponibil stocDisponibil: stocDisponibilList){
            stocDisponibilMap.put(stocDisponibil.getProdusId(),stocDisponibil.getStocDisponibil());
        }
        return  stocDisponibilMap;
    }

    @Override
    public Map<Integer, FurnizorProdus> getProduseLaFurnizor(int divizieId, int firmaId, int furnizorId, List<Integer> produsIdList){
        List<FurnizorProdus> produseFurnizorList = stocRepozitory.getProduseLaFurnizor(furnizorId,firmaId,divizieId, produsIdList);
        Map<Integer, FurnizorProdus> produseFurnizorMap = new HashMap<>(produseFurnizorList.size());
        for (FurnizorProdus fp:produseFurnizorList){
            produseFurnizorMap.put(fp.getProdusId(),fp);
        }
        return produseFurnizorMap;
    }

    @Override
    public Map<Integer, FurnizorProdus> getProduseLaFurnizorPeComanda(int porderCapId){
        Optional<PorderCapDto> optionalPorderCapDto = porderCapService.findDtoById(porderCapId);
        Map<Integer, FurnizorProdus> produseFurnizorMap = new HashMap<>();
        if (optionalPorderCapDto.isPresent()){
            List<PorderPozDto>porderPozDtoList=optionalPorderCapDto.get().getPozitiiDto();
            List<Integer> produsIdsList = porderPozDtoList
                    .stream()
                    .map(PorderPozDto::getProdusId)
                    .toList();
            produseFurnizorMap = getProduseLaFurnizor(
                    optionalPorderCapDto.get().getDivizieId(),
                    optionalPorderCapDto.get().getFirmaId(),
                    optionalPorderCapDto.get().getFurnizorId(),
                    produsIdsList);

        }
        return produseFurnizorMap;
    }


}
