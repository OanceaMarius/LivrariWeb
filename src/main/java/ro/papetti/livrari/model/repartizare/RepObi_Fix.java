package ro.papetti.livrari.model.repartizare;

import lombok.RequiredArgsConstructor;
import ro.papetti.LivrariTabele.dto.ComandaCapDto;
import ro.papetti.LivrariTabele.dto.CoordonateFixeDto;

@RequiredArgsConstructor
public class RepObi_Fix {
    private final CoordonateFixeDto coordonateFixeDto;
    private final ComandaCapDto comandaCapDto;


    public void completeazaDatele(){

        comandaCapDto.setOrderCapId(coordonateFixeDto.getIdCoordonata());
        comandaCapDto.setObsProg(coordonateFixeDto.getNume());

    }



}
