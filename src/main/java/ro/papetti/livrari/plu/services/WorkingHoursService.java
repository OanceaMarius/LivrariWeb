package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.WorkingHoursDto;
import ro.papetti.pluriva.entity.WorkingHours;

import java.util.List;
import java.util.Optional;

public interface WorkingHoursService  extends BaseService<WorkingHours, Integer> {


    Optional<WorkingHours> findEagerById(int workingHoursId);

    List<WorkingHours> findEagerAll();

    Optional<WorkingHoursDto> findDtoById(int workingHoursId);

    List<WorkingHoursDto> findDtoEagerAll();
}
