package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.IntrCapDto;
import ro.papetti.pluriva.entity.IntrCap;

import java.util.Optional;

public interface IntrCapService extends BaseService<IntrCap, Integer> {
    Optional<IntrCapDto> findDtoById(int intrCapId);

    Optional<IntrCap> findEagerById(int intrCapId);
}
