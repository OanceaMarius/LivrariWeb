package ro.papetti.livrari.model.repartizare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.papetti.componente.InfoMarfa;
import ro.papetti.livrari.model.ComandaPluPoz;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class RepCantReceptionate {

    private final InfoMarfa infoMarfa;

    @Autowired
    public RepCantReceptionate(InfoMarfa infoMarfa) {
        this.infoMarfa = infoMarfa;
    }

    public void completeazaCantitatiReceptionate(List<ComandaPluPoz> comandaPluPozList, int porderCapId){

        Map<Integer, BigDecimal> produseReceptionate =
                infoMarfa.getCantitatiReceptionateByPorderCapId(porderCapId);
        for (ComandaPluPoz comPoz:comandaPluPozList){
            comPoz.setCantLivrata(produseReceptionate.get(comPoz.getOrderPozPluId()));
        }
    }


}
