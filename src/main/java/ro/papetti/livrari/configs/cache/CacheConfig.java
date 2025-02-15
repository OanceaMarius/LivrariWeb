package ro.papetti.livrari.configs.cache;

import jakarta.annotation.PostConstruct;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import ro.papetti.livrari.plu.services.*;
import ro.papetti.pluriva.dto.*;
import ro.papetti.pluriva.entity.*;

import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

    final private CacheManager cacheManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UmService umService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CPVService cpvService;
    @Autowired
    private JudetService judetService;
    @Autowired
    private LocalitateService localitateService;
    @Autowired
    private ModPlataService modPlataService;
    @Autowired
    private StareDocService stareDocService;
    @Autowired
    private TaraService taraService;
    @Autowired
    private TermenPlataService termenPlataService;
    @Autowired
    private TipDocService tipDocService;
    @Autowired
    private TipFirmaService tipFirmaService;
    @Autowired
    private TipLivrareService tipLivrareService;
    @Autowired
    private TipStradaService tipStradaService;
    @Autowired
    private TvaService tvaService;
    @Autowired
    private ProdusService produsService;
    @Autowired
    private UnitateService unitateService;



    public CacheConfig(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }




    @PostConstruct
    public void preLoadCache(){


        //User
        Cache cacheUser = cacheManager.getCache(CacheName.USER_DTO);
        List<User> listUsers =userService.findAll();
        for (User user: listUsers){
            cacheUser.put(user.getUserId(),user);
        }

        //UM
        Cache cacheUm = cacheManager.getCache(CacheName.UM_DTO);
        List<UmDTOI> listUmDTO =umService.findDTOAll(UmDTOI.class);
        for (UmDTOI um: listUmDTO) {
            cacheUm.put(um.getUMId(),um);
        }

        //Brands
        Cache cacheBrand = cacheManager.getCache(CacheName.BRAND_DTO);
        List<BrandDTOI> listBrands = brandService.findDTOAll(BrandDTOI.class);
        for (BrandDTOI brandDTOI: listBrands){
            cacheBrand.put(brandDTOI.getBrandId(),brandDTOI);
        }

        //CPV astea nu au DTO
        Cache cacheCPV = cacheManager.getCache(CacheName.CPV_DTO);
        List<CPV> listCPVs = cpvService.findAll();
        for (CPV cpv:listCPVs){
            cacheCPV.put(cpv.getCPVId(), cpv);
        }

        //Judet
        Cache cacheJudet = cacheManager.getCache(CacheName.JUDET_DTO);
        List<JudetDTOI> listJudete = judetService.findDTOAll(JudetDTOI.class);
        for (JudetDTOI judetDTOI: listJudete){
            cacheJudet.put(judetDTOI.getJudetID(), judetDTOI);
        }

        //Localitate
        Cache cacheLocalitate = cacheManager.getCache(CacheName.LOCALITATE_DTO);
        List<LocalitateDTOI> listLocalitati = localitateService.findDTOAll(LocalitateDTOI.class);
        for (LocalitateDTOI localitateDTOI:listLocalitati){
            cacheLocalitate.put(localitateDTOI.getLocalitateID(),localitateDTOI);
        }

        //ModPlata
        Cache cacheModPlata = cacheManager.getCache(CacheName.MOD_PLATA_DTO);
        List<ModPlataDTOI> listModPlata = modPlataService.findDTOAll(ModPlataDTOI.class);
        for (ModPlataDTOI modPlataDTOI:listModPlata){
            cacheModPlata.put(modPlataDTOI.getModPlataId(),modPlataDTOI);
        }

        //StareDoc
        Cache cacheStareDoc = cacheManager.getCache(CacheName.STARE_DOC_DTO);
        List<StareDocDTOI> listStareDoc =stareDocService.findDTOAll(StareDocDTOI.class);
        for (StareDocDTOI stareDocDTOI: listStareDoc)
        {
            cacheStareDoc.put(stareDocDTOI.getStareId(),stareDocDTOI);
        }
        //Tara
        Cache cacheTara = cacheManager.getCache(CacheName.TARA_DTO);
        List<TaraDTOI> listTari = taraService.findDTOAll(TaraDTOI.class);
        for (TaraDTOI taraDTOI:listTari){
            cacheTara.put(taraDTOI.getTaraID(),taraDTOI);
        }

        //TermanPlata
        Cache cacheTermenPlata = cacheManager.getCache(CacheName.TERMEN_PLATA_DTO);
        List<TermenPlataDTOI> listTermenePlata = termenPlataService.findDTOAll(TermenPlataDTOI.class);
        for (TermenPlataDTOI termenPlataDTOI:listTermenePlata)  {
            cacheTermenPlata.put(termenPlataDTOI.getTermenPlataID(),termenPlataDTOI);
        }

        //TipDoc
        Cache cacheTipDoc = cacheManager.getCache(CacheName.TIP_DOC_DTO);
        List<TipDocDTOI> listTipDoc =tipDocService.findDTOAll(TipDocDTOI.class);
        for (TipDocDTOI tipDocDTOI: listTipDoc){
            cacheTipDoc.put(tipDocDTOI.getTipDocId(),tipDocDTOI);
        }

        //TipFirma  nu are DTO ca nu e cazul
        Cache cacheTipFirma = cacheManager.getCache(CacheName.TIP_FIRMA_DTO);
        List<TipFirma> listTipFirma = tipFirmaService.findAll();
        for (TipFirma tipFirma:listTipFirma){
            cacheTipFirma.put(tipFirma.getTipFirmaId(),tipFirma);
        }

        //TipLivrare
        Cache cacheTipLivrare = cacheManager.getCache(CacheName.TIP_LIVRARE_DTO);
        List<TipLivrareDTOI> listTipLivrare =tipLivrareService.findDTOAll(TipLivrareDTOI.class);
        for (TipLivrareDTOI tipLivrareDTOI: listTipLivrare){
            cacheTipLivrare.put(tipLivrareDTOI.getTipLivrareId(),tipLivrareDTOI);
        }

        //TipStrada
        Cache cacheTipStrada = cacheManager.getCache(CacheName.TIP_STRADA_DTO);
        List<TipStrada> tipStradaList = tipStradaService.findAll();
        for (TipStrada tipStrada:tipStradaList){
            cacheTipStrada.put(tipStrada.getTipStradaId(),tipStrada);
        }


        //Tva
        Cache cacheTva = cacheManager.getCache(CacheName.TVA_DTO);
        List<TvaDTOI> tvaList =tvaService.findDTOAll(TvaDTOI.class);
        for (TvaDTOI tvaDTOI: tvaList){
            cacheTva.put(tvaDTOI.getTvaId(),tvaDTOI);
        }

        //Produs
        Cache cacheProdus = cacheManager.getCache(CacheName.PRODUS_DTO);
        List<ProdusDTOI> produsList = produsService.findDTOAll(ProdusDTOI.class);
        for (ProdusDTOI produsDTOI :produsList){
            cacheProdus.put(produsDTOI.getProdusId(),produsDTOI);
        }

        //Unitate dureaza prea mult
//        Cache cacheUnitate = cacheManager.getCache(CacheName.UNITATE_DTO);
//        List<UnitateDTOI> unitateList = unitateService.findDTOAll(UnitateDTOI.class);
//        for (UnitateDTOI unitateDTOI :unitateList){
//            cacheUnitate.put(unitateDTOI.getUnitateID(),unitateDTOI);
//        }



    }


}
