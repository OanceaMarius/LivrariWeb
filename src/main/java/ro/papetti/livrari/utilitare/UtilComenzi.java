/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.utilitare;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import ro.papetti.componente.InfoMarfa;
import ro.papetti.livrari.model.ComandaHarta;
import ro.papetti.livrari.model.ComandaPluPoz;
import ro.papetti.livrari.model.PozCantitate;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.pluriva.dto.SOrderPozDTO;
import ro.papetti.pluriva.dto.SOrderPozDTOI;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
public final class UtilComenzi {

    private UtilComenzi() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * completeaza din comenzile de cllienti Sale pluriva liniile
     *
     * @param listPluDTO
     * @return
     */
    public static List<ComandaPluPoz> getComandaPluPozFromSDTO(List<SOrderPozDTO> listPluDTO) {
        List<ComandaPluPoz> listPoz;
        listPoz = new ArrayList<>();
        if (listPluDTO == null) {
            return listPoz;
        }
        for (SOrderPozDTO pozPluDTO : listPluDTO) {
            ComandaPluPoz liniePoz = new ComandaPluPoz(pozPluDTO);
            //doar liniile care tin de comanda nu si intrarile
            if (pozPluDTO.sOrderPozParentId() == null) {
                listPoz.add(liniePoz);
            }
        }

        return listPoz;
    }
    
        public static List<ComandaPluPoz> getComandaPluPozFromSDTOI(List<SOrderPozDTOI> listPluDTO) {
        List<ComandaPluPoz> listPoz = new ArrayList<>();
        if (listPluDTO == null) {
            return listPoz;
        }
        for (SOrderPozDTOI pozPluDTOI : listPluDTO) {
            ComandaPluPoz liniePoz = new ComandaPluPoz(pozPluDTOI);
            //doar liniile care tin de comanda nu si intrarile
            if (pozPluDTOI.getsOrderPozParentId() == null) {
                listPoz.add(liniePoz);
            }
        }

        return listPoz;
    }
    public static List<ComandaPluPoz> getComandaPluPozFromS(List<SOrderPoz> listPlu) {
        List listPoz = new ArrayList<ComandaPluPoz>();
        if (listPlu == null) {
            return listPoz;
        }
        for (SOrderPoz pozPlu : listPlu) {
            ComandaPluPoz liniePoz = new ComandaPluPoz(pozPlu);
            //doar liniile care tin de comanda nu si intrarile
            if (pozPlu.getsOrderPozParentId() == null) {
                listPoz.add(liniePoz);
            }
        }

        return listPoz;
    }
    /**
     * completeaza din comenzile de furnizori Purch pluriva liniile
     *
     * @param listPlu
     * @return
     */
    public static List<ComandaPluPoz> getComandaPluPozFromP(List<POrderPoz> listPlu) {
        List listPoz = new ArrayList<ComandaPluPoz>();
        if (listPlu == null) {
            return listPoz;
        }

        for (POrderPoz pozPlu : listPlu) {
            ComandaPluPoz liniePoz = new ComandaPluPoz(pozPlu);
            listPoz.add(liniePoz);
        }

        return listPoz;
    }

    /**
     *
     * @param stocuri -lista cu stocurile disponibile
     * @param produsId
     * @return stocul pt produsul curent
     */
    public static BigDecimal getStocPeProdus(Set<StocDisponibil> stocuri, int produsId) {
        Map<Integer, BigDecimal> stocuriMap = stocuri.stream()
                .collect(Collectors.toMap(StocDisponibil::getProdusId, StocDisponibil::getStocDisponibil));
        BigDecimal stoc = stocuriMap.get(produsId);
        if (stoc != null) {
            return stoc;
        }
        return new BigDecimal(0);
    }

    public static BigDecimal getCantitatePePoz(List<PozCantitate> pozitii, int pozId) {
        Map<Integer, BigDecimal> pozitiiMap = pozitii.stream()
                .collect(Collectors.toMap(PozCantitate::getPozId, PozCantitate::getCantitate));
        BigDecimal cant = pozitiiMap.get(pozId);
        if (cant != null) {
            return cant;
        }
        return new BigDecimal(0);
    }

    /**
     * Completeaza stocurile disponibile pentru pozitiile din comanda
     *
     * @param comanda
     * @param stocuri
     */
    public static void putStocuriDisponibile(ComandaHarta comanda, Set<StocDisponibil> stocuri) {
        List<ComandaPluPoz> pozitii = comanda.getPozitiiPluriva();
        for (ComandaPluPoz poz : pozitii) {
            poz.setCantStoc(getStocPeProdus(stocuri, poz.getProdusId()));
        }
    }

    public static void putCantitatiLivrateS(ComandaHarta comanda, InfoMarfa info) {
        List<ComandaPluPoz> listPozPlu = comanda.getPozitiiPluriva();
        if (listPozPlu.isEmpty()) {
            return;
        }
        List<PozCantitate> listCantLiv = info.getCantitatiLivrate(
                comanda.getOrderCapId(),
                comanda.getFirmaId());
        for (ComandaPluPoz poz : listPozPlu) {
            BigDecimal cantLiv = UtilComenzi.getCantitatePePoz(
                    listCantLiv,
                    poz.getOrderPozId());
            poz.setCantLivrata(cantLiv);

        }
    }

    public static void putCantitatiRezervateS(ComandaHarta comanda, InfoMarfa info) {
        List<ComandaPluPoz> listPozPlu = comanda.getPozitiiPluriva();
        if (listPozPlu.isEmpty()) {
            return;
        }
        List<PozCantitate> listCantRez = info.getCantitatiRezervate(comanda.getOrderCapId());
        for (ComandaPluPoz poz : listPozPlu) {
            BigDecimal cantRez = UtilComenzi.getCantitatePePoz(
                    listCantRez,
                    poz.getOrderPozId());
            poz.setCantRezervat(cantRez);

        }
    }

}
