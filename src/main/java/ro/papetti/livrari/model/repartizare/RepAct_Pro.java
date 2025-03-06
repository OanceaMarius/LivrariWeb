package ro.papetti.livrari.model.repartizare;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.dto.ComandaCapDto;
import ro.papetti.pluriva.dto.FollowUpDto;

@RequiredArgsConstructor
@Transactional
public class RepAct_Pro {
    private final ComandaCapDto comandaCapDto;
    private final FollowUpDto followUpDto;


    public void completeazaDatele(){

        comandaCapDto.setObsProg(followUpDto.getDescriereFollowup());

    }



}
