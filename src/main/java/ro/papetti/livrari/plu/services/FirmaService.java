package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.FirmaDto;
import ro.papetti.pluriva.entity.Firma;

import java.util.List;
import java.util.Optional;

public interface FirmaService extends BaseService<Firma, Integer> {


    Optional<Firma> findEagerById(int firmaId);

    List<Firma> findEagerAll();

    Optional<FirmaDto> findDtoById(int firmaId);

    List<FirmaDto> findDtoAll();

}
