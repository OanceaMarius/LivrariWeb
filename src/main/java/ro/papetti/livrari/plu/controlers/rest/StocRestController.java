package ro.papetti.livrari.plu.controlers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ro.papetti.livrari.model.StocDisponibil;
import ro.papetti.livrari.plu.services.StocService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class StocRestController {
    private final StocService stocService;


    @GetMapping("/Stoc/{firmaId}")
    public List<StocDisponibil> getStocDisponibilInGestiuneOperationala(@NonNull @PathVariable int firmaId){
        return stocService.getStocDisponibilInGestiuneOperationala(firmaId);
    }

    @GetMapping("/StocFiltrat/{firmaId}")
    public Map<Integer, BigDecimal> getStocDisponibilInGestiuneOperationalaFiltrat(@NonNull @PathVariable int firmaId, @RequestParam List<Integer> idList){
        if (idList==null)
            idList= new ArrayList<>();

        System.out.println(idList);
        return stocService.getStocDisponibilInGestiuneOperationalaFiltrat(firmaId, idList);
    }


}
