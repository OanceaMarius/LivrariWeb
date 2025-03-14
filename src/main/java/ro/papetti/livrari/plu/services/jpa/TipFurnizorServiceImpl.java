package ro.papetti.livrari.plu.services.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.plu.repozitories.TipFurnizorRepozitory;
import ro.papetti.livrari.plu.services.TipFurnizorService;
import ro.papetti.pluriva.dto.TipFurnizorDto;
import ro.papetti.pluriva.entity.TipFurnizor;
import ro.papetti.pluriva.mapstruct.TipFurnizorMapStruct;
import ro.papetti.pluriva.model.TblTipFurnizorPK;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
@Transactional("plurivaTransactionManager")
public class TipFurnizorServiceImpl implements TipFurnizorService {

    private final TipFurnizorRepozitory tipFurnizorRepozitory;
    private final TipFurnizorMapStruct tipFurnizorMapStruct;


    @Override
    public List<TipFurnizorDto> findDtoAll() {
        List<TipFurnizor>furnizorList=tipFurnizorRepozitory.findAll();
        return tipFurnizorMapStruct.toDtoList(furnizorList);
    }

    @Cacheable(cacheNames = CacheName.TIP_FURNIZOR_DTO, key = "#tipFurnizorId")
    @Override
    public Optional<TipFurnizorDto> findDtoById(int tipFurnizorId) {
        TblTipFurnizorPK pk = new TblTipFurnizorPK(tipFurnizorId,1);
        Optional<TipFurnizor>tipFurnizor=tipFurnizorRepozitory.findById(pk);
        return tipFurnizor.map(tipFurnizorMapStruct::toDto);
    }

    @Override
    public List<TipFurnizor> findAll(){
        return tipFurnizorRepozitory.findAll();
    }

    @Override
    public Optional<TipFurnizor> findById(Integer tipFurnizorId){
        TblTipFurnizorPK pk = new TblTipFurnizorPK(tipFurnizorId,1);
        return tipFurnizorRepozitory.findById(pk);
    }
}
