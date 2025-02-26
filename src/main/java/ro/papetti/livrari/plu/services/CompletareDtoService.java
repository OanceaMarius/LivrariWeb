package ro.papetti.livrari.plu.services;

import ro.papetti.pluriva.dto.*;

import java.util.Optional;

public interface CompletareDtoService {

//    LeadDto getLeadDtoById(Integer id);

    TipFirmaDto getTipFirmaById(Integer id);

    TipActivitateDto getTipActivitateById(Integer id);

    TipDocDto getTipDocById(Integer id);

    TermenPlataDto getTermenPlataById(Integer id);

    ModPlataDto getModPlataById(Integer id);

    TipLivrareDto getTipLivrareById(Integer id);

    UmDto getUmById(Integer id);

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
}
