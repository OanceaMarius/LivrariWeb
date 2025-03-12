package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.IesCapDto;
import ro.papetti.pluriva.entity.IesCap;

import java.util.Optional;

public interface IesCapService extends BaseService<IesCap, Integer> {

    Optional<IesCapDto> findDtoById(int iesCapId);

    Optional<IesCap> findEagerById(int iesCapId);
}
