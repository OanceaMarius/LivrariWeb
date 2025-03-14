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
    private final WorkingHoursService workingHoursService;
    private final GestiuneService gestiuneService;


    @Override
    public WorkingHoursDto getWorkingHoursDtoById(Integer id){
        if (id == null)
            return null;
        return workingHoursService.findDtoById(id).orElse(null);
    }


    @Override
    public TipFirmaDto getTipFirmaDtoById(Integer id) {
        if (id == null)
            return null;
        return tipFirmaService.findDtoById(id).orElse(null);
    }

    @Override
    public TipActivitateDto getTipActivitateDtoById(Integer id) {
        if (id == null)
            return null;
        return tipActivitateService.findDtoById(id).orElse(null);
    }

    @Override
    public TipDocDto getTipDocDtoById(Integer id) {
        if (id == null)
            return null;
        return tipDocService.findDtoById(id).orElse(null);
    }

    @Override
    public DocDto getDocDtoById(Integer id) {
        if (id == null)
            return null;
        return tipDocService.findDocDtoById(id).orElse(null);
    }

    @Override
    public TermenPlataDto getTermenPlataDtoById(Integer id) {
        if (id == null)
            return null;
        return termenPlataService.findDtoById(id).orElse(null);
    }

    @Override
    public ModPlataDto getModPlataDtoById(Integer id) {
        if (id == null)
            return null;
        return modPlataService.findDtoById(id).orElse(null);
    }

    @Override
    public TipLivrareDto getTipLivrareDtoById(Integer id) {
        if (id == null)
            return null;
        return tipLivrareService.findDtoById(id).orElse(null);
    }

    @Override
    public UmDto getUmDtoById(Integer id) {
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



    @Override
    public TipStradaDto getTipStradaDtoById(Integer id) {
        if ((id == null))
            return null;
        return tipStradaService.findDtoById(id).orElse(null);
    }

    @Override
    public GestiuneDto getGestiuneDtoById(Integer id){
        if (id==null)
            return null;

        return gestiuneService.findDtoById(id).orElse(null);
    }


}
