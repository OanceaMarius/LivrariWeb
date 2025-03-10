package ro.papetti.livrari.model.repartizare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.papetti.componente.InfoMarfa;
import ro.papetti.livrari.model.ComandaPluPoz;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class RepStocuri {

    private final InfoMarfa infoMarfa;

    @Autowired
    public RepStocuri(InfoMarfa infoMarfa) {
        this.infoMarfa = infoMarfa;
    }

    public void completeazaStocuri(List<ComandaPluPoz> comandaPluPozList , int firmaId){
        List<Integer> produsIdList = comandaPluPozList.stream().map(ComandaPluPoz::getProdusId).toList();
        Map<Integer, BigDecimal> produseStocMap =
                infoMarfa.getStocDisponibilInGestiuneOperationalaFiltrat(firmaId
                        , produsIdList);
        for (ComandaPluPoz comPoz:comandaPluPozList){
            comPoz.setCantStoc(produseStocMap.get(comPoz.getProdusPluId()));
        }
    }


}
