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

    public <T> Optional<T> findDTOIById(int produsId, Class<T> type) {
        return rep.findDTOIById(produsId,type);
    }


    @Override
    @Cacheable(cacheNames = CacheName.PRODUS_DTO,key = "#produsId")
    public Optional<ProdusDto> findDtoById(int produsId){
        Optional<Produs>produs = rep.findById(produsId);

        setUmDtoFromCache(produs);
        setBrandDtoFromCache(produs);
        setTvaDtoFromCache(produs);
        setCpvDtoFromCache(produs);

        ProdusDto produsDto = produsMapStruct.toDto(produs.get());
        return Optional.of(produsDto);
    };

    @Override
    public List<ProdusDto> findDtoAll(){
        return produsMapStruct.toDtoList(rep.findAll());
    };

    private void setUmDtoFromCache(Optional<Produs> produsOptional){
        if (produsOptional.isEmpty()){
            return;
        }
        Produs produs = produsOptional.get();
        Integer umId = produs.getUmId();
        if (umId == null){
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<UmDto> umDto =umService.findDtoById(umId);
        umDto.ifPresent(produs::setUmDto);

    }

    private void setTvaDtoFromCache(Optional<Produs> produsOptional){
        if (produsOptional.isEmpty()){
            return;
        }
        Produs produs = produsOptional.get();
        Integer tvaId = produs.getTvaId();
        if (tvaId == null){
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<TvaDto> tvaDto =tvaService.findDtoById(tvaId);
        tvaDto.ifPresent(produs::setTvaDto);

    }

    private void setBrandDtoFromCache(Optional<Produs> produsOptional){
        if (produsOptional.isEmpty()){
            return;
        }
        Produs produs = produsOptional.get();
        Integer brandId = produs.getBrandId();
        if (brandId == null){
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<BrandDto> brandDto =brandService.findDtoById(brandId);
        brandDto.ifPresent(produs::setBrandDto);

    }

    private void setCpvDtoFromCache(Optional<Produs> produsOptional){
        if (produsOptional.isEmpty()){
            return;
        }
        Produs produs = produsOptional.get();
        Integer cpvId = produs.getCPVId();
        if (cpvId == null){
            return;
        }
        //aici ul va lua din cache daca e acolo
        Optional<CpvDto> cpvDto =cpvService.findDtoById(cpvId);
        cpvDto.ifPresent(produs::setCpvDto);

    }

}
