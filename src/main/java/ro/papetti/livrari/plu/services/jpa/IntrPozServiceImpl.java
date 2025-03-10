package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.IntrPozRepozitory;
import ro.papetti.livrari.plu.services.CompletareDtoService;
import ro.papetti.livrari.plu.services.IntrPozService;
import ro.papetti.livrari.plu.services.ProdusService;
import ro.papetti.pluriva.dto.IntrPozDto;
import ro.papetti.pluriva.entity.IntrPoz;
import ro.papetti.pluriva.mapstruct.IntrPozMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class IntrPozServiceImpl extends BaseServiceImpl<IntrPoz, IntrPozRepozitory> implements IntrPozService {
    public IntrPozServiceImpl(IntrPozRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private IntrPozMapStruct intrPozMapStruct;
    @Autowired
    private CompletareDtoService completareDtoService;
    @Autowired
    private ProdusService produsService;

    @Override
    public List<IntrPoz> findByIntrCapId(int intrCapId) {
        return rep.findByIntrCapId(intrCapId);
    }

    @Override
    public List<IntrPozDto> findDtoByIntrCapId(int intrCapId){
        List<IntrPoz>intrPozList=findByIntrCapId(intrCapId);
        List<IntrPozDto>intrPozDtoList = intrPozMapStruct.toDtoList(intrPozList);
        for (IntrPozDto dto :intrPozDtoList){
            dto.setGestiuneDto(completareDtoService.getGestiuneDtoById(dto.getGestiuneId()));
            dto.setProdusDto(produsService.findDtoById(dto.getProdusId()).orElse(null));
        }
        return intrPozDtoList;
    }

    @Override
    public Optional<IntrPozDto> findDtoById(int intrPozId){
        Optional<IntrPoz> optionalIntrPoz= findById(intrPozId);
        Optional<IntrPozDto> optionalIntrPozDto = optionalIntrPoz.map(value->intrPozMapStruct.toDto(value));
        if (optionalIntrPozDto.isPresent()){
            optionalIntrPozDto.get().setProdusDto(produsService.findDtoById(optionalIntrPozDto.get().getProdusId()).orElse(null));
            optionalIntrPozDto.get().setGestiuneDto(completareDtoService.getGestiuneDtoById(optionalIntrPozDto.get().getGestiuneId()));
        }
        return optionalIntrPozDto;
    }

    @Override
    public Optional<IntrPoz> findEagerById(int intrPozId){
        return rep.findEagerById(intrPozId);
    }

    @Override
    public List<IntrPoz> findEagerByIntrCapId(int intrCapId){
        return rep.findEagerByIntrCapId(intrCapId);
    }


}
