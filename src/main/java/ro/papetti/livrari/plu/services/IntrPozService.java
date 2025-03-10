package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.IntrPozDto;
import ro.papetti.pluriva.entity.IntrPoz;

import java.util.List;
import java.util.Optional;

public interface IntrPozService extends BaseService<IntrPoz, Integer> {

    List<IntrPoz> findByIntrCapId(int intrCapId);

    List<IntrPozDto> findDtoByIntrCapId(int intrCapId);

    Optional<IntrPozDto> findDtoById(int intrPozId);

    Optional<IntrPoz> findEagerById(int intrPozId);

    List<IntrPoz> findEagerByIntrCapId(int intrCapId);
}
