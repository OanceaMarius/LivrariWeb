package ro.papetti.livrari.plu.controlers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.PorderPozService;
import ro.papetti.livrari.plu.services.SorderPozService;
import ro.papetti.pluriva.dto.SorderCapDto;
import ro.papetti.pluriva.dto.SorderPozDto;
import ro.papetti.pluriva.entity.SorderPoz;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class SorderPozRestController {

    private final SorderPozService sorderPozService;
    private final PorderPozService porderPozService;

    @GetMapping("/SorderCapDTO/PozId/{sorderPozId}")
    public Optional<SorderCapDto> findSorderCapDtoBySorderPozId(@NonNull @PathVariable int sorderPozId) {
        return sorderPozService.findSorderCapDtoBySorderPozId(sorderPozId);
    }

    @GetMapping("/SorderPoz/{sorderPozId}")
    public Optional<SorderPoz> findEagerById(@NonNull @PathVariable int sorderPozId) {
        return sorderPozService.findEagerById(sorderPozId);
    }

    @GetMapping("/SorderPoz/CapId/{sorderCapId}")
    public List<SorderPoz> findEagerBySorderCapId(@NonNull @PathVariable int sorderCapId){
        return sorderPozService.findEagerBySorderCapId(sorderCapId);
    }

    @GetMapping("/SorderPozDTO/CapId/{sorderCapId}")
    public List<SorderPozDto> findPozDtoBySorderCapId(@NonNull @PathVariable int sorderCapId){
        return sorderPozService.findPozDtoBySOrderCapId(sorderCapId);
    }



}
