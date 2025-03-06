package ro.papetti.livrari.model.repartizare;

import lombok.RequiredArgsConstructor;
import ro.papetti.LivrariTabele.dto.ComandaCapDto;
import ro.papetti.LivrariTabele.dto.ComandaPozDto;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.livrari.model.ComandaPluPoz;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.dto.SorderPozDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class RepSorder {
    private final SorderCapDto sorderCapDto;
    private final ComandaCapDto comandaCapDto;

    public List<ComandaPluPoz> getComandaPluPozList() {
        List<ComandaPluPoz> comandaPluPozList = new ArrayList<ComandaPluPoz>();
        List<ComandaPozDto> comandaPozList = comandaCapDto.getPozitiiLivrari();
        Set<Integer> orderPozIdSet = new HashSet<>(comandaCapDto.getPozitiiLivrari().size());
        for (ComandaPozDto poz : comandaPozList) {
            ComandaPluPoz comandaPluPoz = new ComandaPluPoz();

            orderPozIdSet.add(poz.getOrderPozId());
            comandaPluPoz.setOrderPozId(poz.getOrderPozId());
            comandaPluPoz.setCantRepartizata(poz.getCantRepartizata());

            comandaPluPozList.add(comandaPluPoz);
        }

        for (SorderPozDto pozDto: sorderCapDto.getPozitiiDto()){
            if (!orderPozIdSet.contains(pozDto.getSorderPozId())){
                
                ComandaPluPoz comandaPluPoz = new ComandaPluPoz();
                comandaPluPoz.setOrderPozId(pozDto.getSorderPozId());
                comandaPluPoz.setOrderPozPluId(pozDto.getSorderPozId());
                comandaPluPoz.setCantRepartizata(pozDto.getCant());

                comandaPluPozList.add(comandaPluPoz);
            }
        }

        return comandaPluPozList;
    }

}
