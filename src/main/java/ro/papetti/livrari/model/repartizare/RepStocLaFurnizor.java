package ro.papetti.livrari.model.repartizare;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.papetti.componente.InfoMarfa;
import ro.papetti.livrari.model.ComandaPluPoz;
import ro.papetti.pluriva.entity.FurnizorProdus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Component
public class RepStocLaFurnizor {

    private final InfoMarfa infoMarfa;

    @Autowired
    public RepStocLaFurnizor(InfoMarfa infoMarfa) {
        this.infoMarfa = infoMarfa;
    }

    public void completeazaStoculLaFurnizor(List<ComandaPluPoz> comandaPluPozList, int porderCapId){

        Map<Integer, FurnizorProdus> produseLaFurmizorMap =
                infoMarfa.getProduseLaFurnizorPeComanda(porderCapId);
        for (ComandaPluPoz comPoz:comandaPluPozList){
            FurnizorProdus produsFurnizor = produseLaFurmizorMap.get(comPoz.getProdusId());
            if (produsFurnizor!=null)
                try {
                    comPoz.setCantLaFurnizor(new BigDecimal(produsFurnizor.getStoc()));
                }catch (NumberFormatException e){
                    log.info("Nu pot sa transform stocul de la furnizor:"+e.getMessage());
                }
        }
    }


}
