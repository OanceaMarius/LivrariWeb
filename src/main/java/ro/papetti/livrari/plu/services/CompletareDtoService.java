package ro.papetti.livrari.plu.services;

import ro.papetti.pluriva.dto.*;

public interface CompletareDtoService {

//    LeadDto getLeadDtoById(Integer id);

    WorkingHoursDto getWorkingHoursDtoById(Integer id);

    TipFirmaDto getTipFirmaDtoById(Integer id);

    TipActivitateDto getTipActivitateDtoById(Integer id);

    TipDocDto getTipDocDtoById(Integer id);

    TermenPlataDto getTermenPlataDtoById(Integer id);

    ModPlataDto getModPlataDtoById(Integer id);

    TipLivrareDto getTipLivrareDtoById(Integer id);

    UmDto getUmDtoById(Integer id);

    CpvDto getCpvDtoById(Integer id);

    TvaDto getTvaDtoById(Integer id);

    BrandDto getBrandDtoById(Integer id);

    StareDocDto getStareDocDtoById(Integer id);

    TaraDto getTaraDtoById(Integer id);


    JudetDto getJudetDtoById(Integer id);

    LocalitateDto getLocalitateDtoById(Integer id);

    UserDto getUserDtoById(Integer id);

//    UnitateDto getUnitateDtoById(Integer id);

//    PartenerDto getPartenerDtoById(Integer id);

    TipStradaDto getTipStradaDtoById(Integer id);

    GestiuneDto getGestiuneDtoById(Integer id);
}
