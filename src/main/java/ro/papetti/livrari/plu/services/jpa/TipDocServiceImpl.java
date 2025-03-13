package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.TipDocRepozitory;
import ro.papetti.livrari.plu.services.TipDocService;
import ro.papetti.pluriva.dto.DocDto;
import ro.papetti.pluriva.dto.TipDocDto;
import ro.papetti.pluriva.entity.TipDoc;
import ro.papetti.pluriva.mapstruct.TipDocMapStruct;

import java.util.List;
import java.util.Optional;


@Service
@Transactional("plurivaTransactionManager")
public class TipDocServiceImpl extends BaseServiceImpl<TipDoc, TipDocRepozitory> implements TipDocService {
    public TipDocServiceImpl(TipDocRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    TipDocMapStruct tipDocMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }

    @Override
    public <T> Optional<T> findDTOIById(int tipDocId, Class<T> type) {
        return rep.findDTOIById(tipDocId,type);
    }

    @Cacheable(cacheNames = CacheName.TIP_DOC_DTO, key = "#tipDocId")
    @Override
    public Optional<TipDocDto>findDtoById(int tipDocId){
        return findById(tipDocId).map(value->tipDocMapStruct.toDto(value));
    }

    @Override
    public List<TipDocDto>findDtoAll(){
        return tipDocMapStruct.toDtoList(rep.findAll());
    }

    @Cacheable(cacheNames = CacheName.DOC_DTO, key = "#docId")
    @Override
    public Optional<DocDto> findDocDtoById(int docId){
        return rep.findDocDtoById(docId);
    }

    @Override
    public List<DocDto> findDocDtoAll(){
        return rep.findDocDtoAll();
    }
}
