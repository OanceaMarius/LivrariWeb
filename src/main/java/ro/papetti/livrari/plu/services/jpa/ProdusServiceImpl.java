package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.ProdusRepozitory;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;
import ro.papetti.pluriva.entity.Produs;
import ro.papetti.pluriva.mapstruct.ProdusMapStruct;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class ProdusServiceImpl extends BaseServiceImpl<Produs, ProdusRepozitory> implements ProdusService {
    public ProdusServiceImpl(ProdusRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private ProdusMapStruct produsMapStruct;
    @Autowired
    private UmService umService;
    @Autowired
    private TvaService tvaService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CpvService cpvService;


    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override
    public <T> List<T> findEagerDTOIAll() {
        return rep.findEagerDTOIAll();
    }

    @Override
    public <T> Optional<T> findDTOIById(int produsId, Class<T> type) {
        return rep.findDTOIById(produsId, type);
    }


    @Override
    @Cacheable(cacheNames = CacheName.PRODUS_DTO, key = "#produsId")
    public Optional<ProdusDto> findDtoById(int produsId) {
        Optional<Produs> produs = rep.findById(produsId);
        Optional<ProdusDto> optionalProdusDto = produs.map( value -> produsMapStruct.toDto(value));
        optionalProdusDto.ifPresent(this::setDtoFromCache);
        return optionalProdusDto;
    }

    @Override
    public Optional<Produs> findEagerById(int produsId){
        return rep.findEagerById(produsId);
    }

    @Override
    public Optional<Produs> findById(int produsID){
        return rep.findById(produsID);
    }

    ;

    @Override
    public List<ProdusDto> findDtoAll() {
        List<Produs> produsList= rep.findAll();
        List<ProdusDto> produsDtoList = produsMapStruct.toDtoList(produsList);
        for (ProdusDto produsDto: produsDtoList){
            setDtoFromCache(produsDto);
        }
        return produsDtoList;
    }

    private void setDtoFromCache(ProdusDto produsDto) {
        setUmDtoFromCache(produsDto);
        setBrandDtoFromCache(produsDto);
        setCpvDtoFromCache(produsDto);
        setTvaDtoFromCache(produsDto);
    }

    ;



    private void setUmDtoFromCache(ProdusDto produsDto) {
        Integer umId = produsDto.getUmId();
        if (umId == null  || produsDto.getUmDto() != null) {
            System.out.println("NU pun DTO din cache pt. umId: " +umId );
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<UmDto> umDto = umService.findDtoById(umId);
        umDto.ifPresent(produsDto::setUmDto);

    }



    private void setTvaDtoFromCache(ProdusDto produsDto) {
        Integer tvaId = produsDto.getTvaId();
        if (tvaId == null  || produsDto.getTvaDto() != null) {
            System.out.println("NU pun DTO din cache pt. tvaId: " +tvaId );
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<TvaDto> tvaDto = tvaService.findDtoById(tvaId);
        tvaDto.ifPresent(produsDto::setTvaDto);

    }



    private void setBrandDtoFromCache(ProdusDto produsDto) {
        Integer brandId = produsDto.getBrandId();
        if (brandId == null  || produsDto.getBrandDto() != null) {
            System.out.println("NU pun DTO din cache pt. brandId: " +brandId );
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<BrandDto> brandDto = brandService.findDtoById(brandId);
        brandDto.ifPresent(produsDto::setBrandDto);

    }


    private void setCpvDtoFromCache(ProdusDto produsDto) {
        Integer cpvId = produsDto.getCpvId();
        if (cpvId == null  || produsDto.getCpvDto() != null) {
            System.out.println("NU pun DTO din cache pt. cpvId: " +cpvId );
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<CpvDto> cpvDto = cpvService.findDtoById(cpvId);
        cpvDto.ifPresent(produsDto::setCpvDto);

    }

}
