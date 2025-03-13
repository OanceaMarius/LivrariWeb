package ro.papetti.livrari.plu.services.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.FirmaRepozitory;
import ro.papetti.livrari.plu.services.FirmaService;
import ro.papetti.livrari.plu.services.PartenerService;
import ro.papetti.pluriva.dto.FirmaDto;
import ro.papetti.pluriva.entity.Firma;
import ro.papetti.pluriva.mapstruct.FirmaMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class FirmaServiceImpl extends BaseServiceImpl<Firma, FirmaRepozitory> implements FirmaService {
    private final FirmaMapStruct firmaMapStruct;
    private final PartenerService partenerService;

    public FirmaServiceImpl(FirmaRepozitory repozitory, FirmaMapStruct firmaMapStruct, PartenerService partenerService) {
        super(repozitory);
        this.firmaMapStruct = firmaMapStruct;
        this.partenerService = partenerService;
    }

    @Override
    public Optional<Firma> findEagerById(int firmaId){
        return rep.findEagerById(firmaId);
    }

    @Override
    public List<Firma> findEagerAll(){
        return rep.findEagerAll();
    }

    @Override
    public Optional<FirmaDto> findDtoById(int firmaId){
        Optional<Firma> optionalFirma = findById(firmaId);
        Optional<FirmaDto> optionalFirmaDto= optionalFirma.map(firmaMapStruct::toDto);
        optionalFirmaDto.ifPresent(firmaDto ->
                firmaDto.setPartenerFirmaDto(partenerService.findDtoById(firmaDto.getPartenerFirmaId()).orElse(null)));

        return optionalFirmaDto;
    }

    @Override
    public List<FirmaDto> findDtoAll() {
        List<Firma>firmaList = findAll();
        List<FirmaDto>firmaDtoList = firmaMapStruct.toDtoList(firmaList);
        for (FirmaDto dto:firmaDtoList){
            dto.setPartenerFirmaDto(partenerService.findDtoById(dto.getPartenerFirmaId()).orElse(null));
        }
        return firmaDtoList;
    }


}
