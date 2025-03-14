package ro.papetti.livrari.plu.services.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.LimitePartenerClientRepozitory;
import ro.papetti.livrari.plu.services.FirmaService;
import ro.papetti.livrari.plu.services.LimitePartenerClientService;
import ro.papetti.livrari.plu.services.UnitateService;
import ro.papetti.pluriva.entity.Firma;
import ro.papetti.pluriva.entity.LimitePartenerClient;
import ro.papetti.pluriva.entity.Unitate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional("plurivaTransactionManager")
public class LimitePartenerClientServiceImpl extends BaseServiceImpl<LimitePartenerClient, LimitePartenerClientRepozitory>
        implements LimitePartenerClientService {
    public LimitePartenerClientServiceImpl(LimitePartenerClientRepozitory repozitory, UnitateService unitateService, FirmaService firmaService) {
        super(repozitory);
        this.unitateService = unitateService;
        this.firmaService = firmaService;
    }

    private final UnitateService unitateService;
    private final FirmaService firmaService;

    @Override
    public List<LimitePartenerClient>findEagerAll(){
        return rep.findEagerAll();
    }

    @Override
    public Optional<LimitePartenerClient> findEagerByIdWithDivizie_1(int partenerFirmaId, int partenerId){
        return rep.findEagerByIdWithDivizieId_1(partenerFirmaId,partenerId);
    }

    @Override
    public Optional<LimitePartenerClient> findEagerByClientIdWithDivizie_1(int firmaId, int clientId){
        Integer partenerId = unitateService.findById(clientId).map(Unitate::getPartenerID).orElse(null);
        Integer partenerFirmaId = firmaService.findById(firmaId).map(Firma::getPartenerFirmaId).orElse(null);
        if ((partenerFirmaId !=null) && (partenerId !=null)) {
            return rep.findEagerByIdWithDivizieId_1(partenerFirmaId, partenerId);
        }
        return Optional.empty();
    }

    @Override
    public boolean isBlocat(int firmaId, int clientId){
        Integer partenerId = unitateService.findById(clientId).map(Unitate::getPartenerID).orElse(null);
        Integer partenerFirmaId = firmaService.findById(firmaId).map(Firma::getPartenerFirmaId).orElse(null);
        if ((partenerFirmaId !=null) && (partenerId !=null)){
            Optional<LimitePartenerClient> optionalLimitePartenerClient = rep.findByIdWithDivizieId_1(partenerFirmaId, partenerId);
            if (optionalLimitePartenerClient.isPresent()) {
                Boolean blocat = optionalLimitePartenerClient.get().getBlocat();
                Integer zileVechimeScadenta = optionalLimitePartenerClient.get().getZileVechimeScadenta();
                log.info("zileVechimeScadenta: {}",zileVechimeScadenta);
                Integer zileBlocare =optionalLimitePartenerClient.get().getZileBlocare();
                log.info("zileBlocare: {}",zileBlocare);
                LocalDateTime dataDeblocare = optionalLimitePartenerClient.get().getDataDeblocare();
                log.info("dataDeblocare: {}",dataDeblocare);

                return rep.clientBlocat(blocat,zileVechimeScadenta,zileBlocare,dataDeblocare,1);
            }
        }
        return false;
    }

}
