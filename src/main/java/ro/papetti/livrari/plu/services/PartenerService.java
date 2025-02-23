package ro.papetti.livrari.plu.services;

import org.springframework.lang.NonNull;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.PartenerDto;
import ro.papetti.pluriva.entity.Partener;

import java.util.List;
import java.util.Optional;

public interface PartenerService extends BaseService<Partener, Integer> {


    Optional<Partener> findEagerByPartenerID(int partenerID);

    Optional<Partener> findById(int partenerID);

    Optional<PartenerDto> findDtoById(@NonNull int partenerId);

    List<PartenerDto> findDtoAll();
}
