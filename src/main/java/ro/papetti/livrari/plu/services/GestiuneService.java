package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.GestiuneDto;
import ro.papetti.pluriva.entity.Gestiune;

import java.util.List;
import java.util.Optional;

public interface GestiuneService extends BaseService<Gestiune, Integer> {

    Optional<GestiuneDto> findDtoById(int gestiuneId);

    List<GestiuneDto> findDtoAll();
}
