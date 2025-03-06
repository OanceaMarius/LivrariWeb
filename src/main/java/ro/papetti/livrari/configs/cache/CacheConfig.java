package ro.papetti.livrari.configs.cache;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;
import ro.papetti.pluriva.entity.Judet;
import ro.papetti.pluriva.entity.TipFirma;
import ro.papetti.pluriva.entity.TipStrada;
import ro.papetti.pluriva.mapstruct.JudetMapStruct;

import java.util.List;
import java.util.prefs.PreferencesFactory;

@RequiredArgsConstructor
@Configuration
@EnableCaching
public class CacheConfig {

    private final  CacheManager cacheManager;
    private final  UserService userService;
    private final  UmService umService;
    private final  BrandService brandService;
    private final  CpvService cpvService;
    private final  JudetService judetService;
    private final  LocalitateService localitateService;
    private final  ModPlataService modPlataService;
    private final  StareDocService stareDocService;
    private final  TaraService taraService;
    private final  TermenPlataService termenPlataService;
    private final  TipDocService tipDocService;
    private final  TipFirmaService tipFirmaService;
    private final  TipLivrareService tipLivrareService;
    private final  TipStradaService tipStradaService;
    private final  TvaService tvaService;
    private final  ProdusService produsService;
    private final  UnitateService unitateService;
    private final  JudetMapStruct judetMapStruct;
    private final  TipActivitateService tipActivitateService;
    private final  WorkingHoursService workingHoursService;
    private final ContactService contactService;




    @PostConstruct
    public void preLoadCache(){

        //WorkingHours
        Cache cacheWorkingHours=cacheManager.getCache(CacheName.WORKING_HOURS);
        List<WorkingHoursDto> hoursDtos=workingHoursService.findDtoEagerAll();
        for (WorkingHoursDto dto:hoursDtos){
            cacheWorkingHours.put(dto.getWorkingHoursId(),dto);
        }

        //Contact
//        Cache cacheContact=cacheManager.getCache(CacheName.CONTACT);
//        List<ContactDto> contactDtos =contactService.findDtoAll();
//        for (ContactDto dto:contactDtos){
//            cacheContact.put(dto.getContactId(),dto);
//        }


        //User
        Cache cacheUser = cacheManager.getCache(CacheName.USER_DTO);
        List<UserDto> listUsers =userService.findDtoAll();
        for (UserDto userDto: listUsers){
            cacheUser.put(userDto.getUserId(),userDto);
        }

        //UM
        Cache cacheUm = cacheManager.getCache(CacheName.UM_DTO);
        List<UmDto> umDtoList =umService.findDtoAll();
        for (UmDto umDto: umDtoList) {
            cacheUm.put(umDto.getUmId(),umDto);
        }

        //Brands
        Cache cacheBrand = cacheManager.getCache(CacheName.BRAND_DTO);
        List<BrandDto> listBrands = brandService.findDtoAll();
        for (BrandDto brandDto: listBrands){
            cacheBrand.put(brandDto.getBrandId(),brandDto);
        }

        //CPV
        Cache cacheCpv = cacheManager.getCache(CacheName.CPV_DTO);
        List<CpvDto> cpvDtoList = cpvService.findDtoAll();
        for (CpvDto cpvDto:cpvDtoList){
            if (cpvDto.getCPVId()!=null)
                System.out.println("CPVID+++++"+cpvDto.getCPVId());
                cacheCpv.put(cpvDto.getCPVId(), cpvDto);
        }

        //Judet
        Cache cacheJudet = cacheManager.getCache(CacheName.JUDET_DTO);
        List<Judet> listJudete = judetService.findAll();

        for (JudetDto judetDto: judetMapStruct.toDtoList(listJudete)){
            cacheJudet.put(judetDto.getJudetID(), judetDto);
        }

        //Localitate
        Cache cacheLocalitate = cacheManager.getCache(CacheName.LOCALITATE_DTO);
        List<LocalitateDto> listLocalitati = localitateService.findDtoAll();
        for (LocalitateDto localitateDto:listLocalitati){
            cacheLocalitate.put(localitateDto.getLocalitateID(),localitateDto);
        }

        //ModPlata
        Cache cacheModPlata = cacheManager.getCache(CacheName.MOD_PLATA_DTO);
        List<ModPlataDto> modPlataDtoList = modPlataService.findDtoAll();
        for (ModPlataDto modPlataDto:modPlataDtoList){
            cacheModPlata.put(modPlataDto.getModPlataId(),modPlataDto);
        }

        //StareDoc
        Cache cacheStareDoc = cacheManager.getCache(CacheName.STARE_DOC_DTO);
        List<StareDocDto> stareDocDtoList =stareDocService.findDtoAll();
        for (StareDocDto stareDocDto: stareDocDtoList)
        {
            cacheStareDoc.put(stareDocDto.getStareId(),stareDocDto);
        }
        //Tara
        Cache cacheTara = cacheManager.getCache(CacheName.TARA_DTO);
        List<TaraDto> taraDtoList = taraService.findDtoAll();
        for (TaraDto taraDto:taraDtoList){
            cacheTara.put(taraDto.getTaraID(),taraDto);
        }

        //TermanPlata
        Cache cacheTermenPlata = cacheManager.getCache(CacheName.TERMEN_PLATA_DTO);
        List<TermenPlataDto> termenPlataDtoList = termenPlataService.findDtoAll();
        for (TermenPlataDto termenPlataDto:termenPlataDtoList)  {
            cacheTermenPlata.put(termenPlataDto.getTermenPlataID(),termenPlataDto);
        }

        //TipDoc
        Cache cacheTipDoc = cacheManager.getCache(CacheName.TIP_DOC_DTO);
        List<TipDocDto> tipDocDtoList =tipDocService.findDtoAll();
        for (TipDocDto tipDocDto: tipDocDtoList){
            cacheTipDoc.put(tipDocDto.getTipDocId(),tipDocDto);
        }

        //TipActivitate
        Cache cacheTipActivitate = cacheManager.getCache(CacheName.TIP_ACTIVITATE);
        List<TipActivitateDto> tipActivitateDtoList =tipActivitateService.findDtoAll();
        for (TipActivitateDto tipActivitateDto: tipActivitateDtoList){
            cacheTipActivitate.put(tipActivitateDto.getTipActivitateID(),tipActivitateDto);
        }

        //TipFirma
        Cache cacheTipFirma = cacheManager.getCache(CacheName.TIP_FIRMA_DTO);
        List<TipFirmaDto> tipFirmaDtoList = tipFirmaService.findDtoAll();
        for (TipFirmaDto tipFirmaDto:tipFirmaDtoList){
            cacheTipFirma.put(tipFirmaDto.getTipFirmaId(),tipFirmaDto);
        }

        //TipLivrare
        Cache cacheTipLivrare = cacheManager.getCache(CacheName.TIP_LIVRARE_DTO);
        List<TipLivrareDto> tipLivrareDtoList =tipLivrareService.findDtoAll();
        for (TipLivrareDto tipLivrareDto: tipLivrareDtoList){
            cacheTipLivrare.put(tipLivrareDto.getTipLivrareId(),tipLivrareDto);
        }

        //TipStrada
        Cache cacheTipStrada = cacheManager.getCache(CacheName.TIP_STRADA_DTO);
        List<TipStradaDto> stradaDtoList = tipStradaService.findDtoAll();
        for (TipStradaDto tipStradaDto:stradaDtoList){
            cacheTipStrada.put(tipStradaDto.getTipStradaId(),tipStradaDto);
        }


        //Tva
        Cache cacheTva = cacheManager.getCache(CacheName.TVA_DTO);
        List<TvaDto> tvaDtoList =tvaService.findDtoAll();
        for (TvaDto tvaDto: tvaDtoList){
            cacheTva.put(tvaDto.getTvaId(),tvaDto);
        }

        //Produs
//        Cache cacheProdus = cacheManager.getCache(CacheName.PRODUS_DTO);
//        List<ProdusDto> produsDtoList = produsService.findDtoAll();
//        for (ProdusDto produsDto :produsDtoList){
//            cacheProdus.put(produsDto.getProdusId(),produsDto);
//        }

        //Unitate dureaza prea mult
//        Cache cacheUnitate = cacheManager.getCache(CacheName.UNITATE_DTO);
//        List<UnitateDto> unitateDtoList = unitateService.findDtoAll();
//        for (UnitateDto unitateDto :unitateDtoList){
//            cacheUnitate.put(unitateDto.getUnitateID(),unitateDto);
//        }



    }


}
