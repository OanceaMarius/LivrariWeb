package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.IesPozDto;
import ro.papetti.pluriva.entity.IesPoz;

import java.util.List;
import java.util.Optional;

public interface IesPozService extends BaseService<IesPoz, Integer> {

    List<IesPoz> findByIesCapId(int iesCapId);

    List<IesPozDto> findDtoByIesCapId(int iesCapId);

    Optional<IesPozDto> findDtoById(int iesPozId);

    Optional<IesPoz> findEagerById(int iesPozId);

    List<IesPoz> findEagerByIesCapId(int iesCapId);
}
