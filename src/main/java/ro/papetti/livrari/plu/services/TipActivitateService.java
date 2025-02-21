package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.TipActivitateDto;
import ro.papetti.pluriva.entity.TipActivitate;

import java.util.List;
import java.util.Optional;

public interface TipActivitateService extends BaseService<TipActivitate, Integer> {

    Optional<TipActivitateDto> findDtoById(int tipActivitateID);

    List<TipActivitateDto> findDtoAll();

}
