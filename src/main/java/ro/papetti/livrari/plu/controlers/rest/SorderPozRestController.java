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
import ro.papetti.pluriva.dto.PorderPozDto;
import ro.papetti.pluriva.dto.SorderCapDto;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class SorderPozRestController {

    private final SorderPozService sorderPozService;
    private final PorderPozService porderPozService;

    @GetMapping("/SorderCapDTO/SorderPozId/{sorderPozId}")
    public Optional<SorderCapDto> findSorderCapDtoBySorderPozId(@NonNull @PathVariable int sorderPozId) {
        return sorderPozService.findSorderCapDtoBySorderPozId(sorderPozId);
    }

}
