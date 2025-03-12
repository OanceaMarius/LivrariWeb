package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.IesPozRepozitory;
import ro.papetti.livrari.plu.services.CompletareDtoService;
import ro.papetti.livrari.plu.services.IesPozService;
import ro.papetti.livrari.plu.services.ProdusService;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.dto.IesPozDto;
import ro.papetti.pluriva.entity.IesPoz;
import ro.papetti.pluriva.mapstruct.IesPozMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class IesPozServiceImpl extends BaseServiceImpl<IesPoz, IesPozRepozitory> implements IesPozService {
    public IesPozServiceImpl(IesPozRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private IesPozMapStruct iesPozMapStruct;
    @Autowired
    private CompletareDtoService completareDtoService;
    @Autowired
    private UnitateService unitateService;
    @Autowired
    private ProdusService produsService;

    @Override
    public List<IesPoz> findByIesCapId(int iesCapId) {
        return rep.findByIesCapId(iesCapId);
    }

    @Override
    public List<IesPozDto> findDtoByIesCapId(int iesCapId) {
        List<IesPoz>iesPozList= rep.findByIesCapId(iesCapId);

        List<IesPozDto> iesPozDtoList = iesPozMapStruct.toDtoList(iesPozList);
        for (IesPozDto dto:iesPozDtoList){
            dto.setProdusDto(produsService.findDtoById(dto.getProdusId()).orElse(null));
            dto.setFurnizorUnitateDto(unitateService.findDtoById(dto.getFurnizorId()).orElse(null));
            dto.setGestiuneDto(completareDtoService.getGestiuneDtoById(dto.getGestiuneId()));
        }
        return iesPozDtoList;
    }

    @Override
    public Optional<IesPozDto> findDtoById(int iesPozId) {
        Optional<IesPoz>optionalIesPoz = rep.findById(iesPozId);
        Optional<IesPozDto>optionalIesPozDto= optionalIesPoz.map(value->iesPozMapStruct.toDto(value));
        if (optionalIesPozDto.isPresent()) {
            optionalIesPozDto.get().setGestiuneDto(completareDtoService.getGestiuneDtoById(optionalIesPozDto.get().getGestiuneId()));
            optionalIesPozDto.get().setFurnizorUnitateDto(unitateService.findDtoById(optionalIesPozDto.get().getFurnizorId()).orElse(null));
            optionalIesPozDto.get().setProdusDto(produsService.findDtoById(optionalIesPozDto.get().getProdusId()).orElse(null));
        }
        return optionalIesPozDto;
    }

    @Override
    public Optional<IesPoz> findEagerById(int iesPozId) {
        return rep.findEagerById(iesPozId);
    }

    @Override
    public List<IesPoz> findEagerByIesCapId(int iesCapId) {
        return rep.findEagerByIesCapId(iesCapId);
    }
}
