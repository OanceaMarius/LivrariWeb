package ro.papetti.livrari.plu.services.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;


@RequiredArgsConstructor
@Service
@Transactional("plurivaTransactionManager")
public class CompletareDtoServiceImpl implements CompletareDtoService {
    private final TaraService taraService;
    private final JudetService judetService;
    private final LocalitateService localitateService;
    private final UserService userService;
    //    private final UnitateService unitateService;
//    private final PartenerService partenerService;
    private final UmService umService;
    private final CpvService cpvService;
    private final TvaService tvaService;
    private final BrandService brandService;
    private final TipStradaService tipStradaService;
    private final TipLivrareService tipLivrareService;
    private final TipDocService tipDocService;
    private final TipActivitateService tipActivitateService;
    private final StareDocService stareDocService;
    private final ModPlataService modPlataService;
    private final TermenPlataService termenPlataService;
    private final TipFirmaService tipFirmaService;
//    private final LeadService leadService;


//    @Override
//    public LeadDto getLeadDtoById(Integer id){
//        if (id==null)
//            return null;
//        return leadService.findDtoById(id).orElse(null);
//    }

    @Override
    public TipFirmaDto getTipFirmaById(Integer id) {
        if (id == null)
            return null;
        return tipFirmaService.findDtoById(id).orElse(null);
    }

    @Override
    public TipActivitateDto getTipActivitateById(Integer id) {
        if (id == null)
            return null;
        return tipActivitateService.findDtoById(id).orElse(null);
    }

    @Override
    public TipDocDto getTipDocById(Integer id) {
        if (id == null)
            return null;
        return tipDocService.findDtoById(id).orElse(null);
    }

    @Override
    public TermenPlataDto getTermenPlataById(Integer id) {
        if (id == null)
            return null;
        return termenPlataService.findDtoById(id).orElse(null);
    }

    @Override
    public ModPlataDto getModPlataById(Integer id) {
        if (id == null)
            return null;
        return modPlataService.findDtoById(id).orElse(null);
    }

    @Override
    public TipLivrareDto getTipLivrareById(Integer id) {
        if (id == null)
            return null;
        return tipLivrareService.findDtoById(id).orElse(null);
    }

    @Override
    public UmDto getUmById(Integer id) {
        if (id == null)
            return null;
        return umService.findDtoById(id).orElse(null);
    }

    @Override
    public CpvDto getCpvDtoById(Integer id) {
        if (id == null)
            return null;
        return cpvService.findDtoById(id).orElse(null);
    }

    @Override
    public TvaDto getTvaDtoById(Integer id) {
        if (id == null)
            return null;
        return tvaService.findDtoById(id).orElse(null);
    }

    @Override
    public BrandDto getBrandDtoById(Integer id) {
        if (id == null)
            return null;
        return brandService.findDtoById(id).orElse(null);
    }

    @Override
    public StareDocDto getStareDocDtoById(Integer id) {
        if (id == null)
            return null;
        return stareDocService.findDtoById(id).orElse(null);
    }

    @Override
    public TaraDto getTaraDtoById(Integer id) {
        if (id == null)
            return null;
        return taraService.findDtoById(id).orElse(null);
    }

    @Override
    public JudetDto getJudetDtoById(Integer id) {
        if ((id == null))
            return null;
        return judetService.findDtoById(id).orElse(null);
    }

    @Override
    public LocalitateDto getLocalitateDtoById(Integer id) {
        if ((id == null))
            return null;
        return localitateService.findDtoById(id).orElse(null);
    }

    @Override
    public UserDto getUserDtoById(Integer id) {
        if ((id == null))
            return null;
        return userService.findDtoById(id).orElse(null);
    }

//    @Override
//    public UnitateDto getUnitateDtoById(Integer id){
//        if ((id==null))
//            return null;
//        return unitateService.findDtoById(id).orElse(null);
//    }



    @Override
    public TipStradaDto getTipStradaDtoById(Integer id) {
        if ((id == null))
            return null;
        return tipStradaService.findDtoById(id).orElse(null);
    }
}
