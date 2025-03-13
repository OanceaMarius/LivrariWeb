package ro.papetti.livrari.model.repartizare;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.papetti.LivrariTabele.dto.ComandaCapDto;
import ro.papetti.LivrariTabele.dto.ComandaPozDto;
import ro.papetti.livrari.model.ComandaPluPoz;
import ro.papetti.pluriva.dto.PorderCapDto;
import ro.papetti.pluriva.dto.PorderPozDto;

import java.util.*;
/**
 * Completeaza liniile si informatiile din comanda din pluriva, stocuri
 */
@Component
@RequiredArgsConstructor
public class RepPorder {
    private  PorderCapDto porderCapDto;
    private  ComandaCapDto comandaCapDto;

    private static RepStocuri repStocuri;
    private static RepCantReceptionate repCantReceptionate;
    private static RepStocLaFurnizor repStocLaFurnizor;

    @Autowired
    public RepPorder(RepStocuri repStocuri, RepCantReceptionate repCantReceptionate, RepStocLaFurnizor repStocLaFurnizor) {
        RepPorder.repStocuri = repStocuri;
        RepPorder.repCantReceptionate=repCantReceptionate;
        RepPorder.repStocLaFurnizor=repStocLaFurnizor;
    }

    public RepPorder(PorderCapDto porderCapDto, ComandaCapDto comandaCapDto) {
        this.porderCapDto = porderCapDto;
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

        //pozitiile din comanda de furnizor
        for (PorderPozDto pozDto: porderCapDto.getPozitiiDto()){
            //daca pozitia din pluriva nu e repartizata
            if (!integerComandaPluPozMap.containsKey(pozDto.getPorderPozId())){
                ComandaPluPoz comandaPluPoz = new ComandaPluPoz();

                comandaPluPoz.setProdusId(pozDto.getProdusId());
                comandaPluPoz.setProdusPluId(pozDto.getProdusId());
                comandaPluPoz.setOrderPozPluId(pozDto.getPorderPozId());
                comandaPluPoz.setCantComanda(pozDto.getCant());

                integerComandaPluPozMap.put(pozDto.getPorderPozId(),comandaPluPoz);
                comandaPluPozList.add(comandaPluPoz);
            }else{ //daca pozitia e deja repartizata
                ComandaPluPoz comandaPluPoz = integerComandaPluPozMap.get(pozDto.getPorderPozId());

                comandaPluPoz.setProdusPluId(pozDto.getProdusId());
                comandaPluPoz.setOrderPozPluId(pozDto.getPorderPozId());
                comandaPluPoz.setCantComanda(pozDto.getCant());
            }
        }
        //pun si stocurile
        repStocuri.completeazaStocuri(comandaPluPozList, comandaCapDto.getFirmaId());
        //pun cantitatilr receptionate
        repCantReceptionate.completeazaCantitatiReceptionate(comandaPluPozList, comandaCapDto.getOrderCapId());
        //pun stocul la furnizor fin feed
        repStocLaFurnizor.completeazaStoculLaFurnizor(comandaPluPozList,  comandaCapDto.getOrderCapId());
        return comandaPluPozList;
    }

}
