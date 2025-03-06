package ro.papetti.livrari.model.repartizare;

import lombok.RequiredArgsConstructor;
import ro.papetti.LivrariTabele.dto.ComandaCapDto;
import ro.papetti.LivrariTabele.dto.ComandaPozDto;
import ro.papetti.LivrariTabele.entity.ComandaPoz;
import ro.papetti.livrari.model.ComandaPluPoz;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.PorderPozDto;

import java.util.*;

@RequiredArgsConstructor
public class RepPorder {
    private final PorderCapDto porderCapDto;
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

        for (PorderPozDto pozDto: porderCapDto.getPozitiiDto()){
            if (!orderPozIdSet.contains(pozDto.getPorderPozId())){

                ComandaPluPoz comandaPluPoz = new ComandaPluPoz();
                comandaPluPoz.setOrderPozId(pozDto.getPorderPozId());
                comandaPluPoz.setOrderPozPluId(pozDto.getPorderPozId());
                comandaPluPoz.setCantRepartizata(pozDto.getCant());

                comandaPluPozList.add(comandaPluPoz);
            }
        }

        return comandaPluPozList;
    }

}
