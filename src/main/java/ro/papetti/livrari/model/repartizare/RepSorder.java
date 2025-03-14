package ro.papetti.livrari.model.repartizare;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.papetti.LivrariTabele.dto.ComandaCapDto;
import ro.papetti.LivrariTabele.dto.ComandaPozDto;
import ro.papetti.livrari.model.ComandaPluPoz;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.dto.SorderPozDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Completeaza liniile si informatiile din comanda din pluriva, stocuri
 */

@Component
@RequiredArgsConstructor
public class RepSorder {
    private SorderCapDto sorderCapDto;
    private  ComandaCapDto comandaCapDto;

    private static RepStocuri repStocuri;

    @Autowired
    public RepSorder(RepStocuri repStocuri) {
        RepSorder.repStocuri = repStocuri;
    }

    public RepSorder(SorderCapDto sorderCapDto, ComandaCapDto comandaCapDto) {
        this.sorderCapDto = sorderCapDto;
        this.comandaCapDto = comandaCapDto;
    }

    public List<ComandaPluPoz> getComandaPluPozList() {
        List<ComandaPluPoz> comandaPluPozList = new ArrayList<ComandaPluPoz>();
        List<ComandaPozDto> comandaPozList = comandaCapDto.getPozitiiLivrari();
        Map<Integer, ComandaPluPoz> integerComandaPluPozMap = new HashMap<>();


        //pozitiile deja repartizate
        for (ComandaPozDto poz : comandaPozList) {
            ComandaPluPoz comandaPluPoz = new ComandaPluPoz();

            comandaPluPoz.setProdusId(poz.getProdusId());
            comandaPluPoz.setOrderPozId(poz.getOrderPozId());
            comandaPluPoz.setCantRepartizata(poz.getCantRepartizata());

            integerComandaPluPozMap.put(poz.getOrderPozId(),comandaPluPoz);
            comandaPluPozList.add(comandaPluPoz);
        }

        //pozitiile din comanda
        for (SorderPozDto pozDto: sorderCapDto.getPozitiiDto()){
            if (!integerComandaPluPozMap.containsKey(pozDto.getSorderPozId())){
                ComandaPluPoz comandaPluPoz = new ComandaPluPoz();

                comandaPluPoz.setProdusId(pozDto.getProdusId());
                comandaPluPoz.setProdusPluId(pozDto.getProdusId());
                comandaPluPoz.setOrderPozPluId(pozDto.getSorderPozId());
                comandaPluPoz.setCantComanda(pozDto.getCant());

                integerComandaPluPozMap.put(pozDto.getSorderPozId(),comandaPluPoz);
                comandaPluPozList.add(comandaPluPoz);
            }else{
                ComandaPluPoz comandaPluPoz = integerComandaPluPozMap.get(pozDto.getSorderPozId());

                comandaPluPoz.setProdusPluId(pozDto.getProdusId());
                comandaPluPoz.setOrderPozPluId(pozDto.getSorderPozId());
                comandaPluPoz.setCantComanda(pozDto.getCant());
            }
        }
        //pun si stocurile
        repStocuri.completeazaStocuri(comandaPluPozList, comandaCapDto.getFirmaId());


        return comandaPluPozList;
    }

}
