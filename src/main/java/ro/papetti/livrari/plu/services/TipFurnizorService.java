package ro.papetti.livrari.plu.services;

import ro.papetti.pluriva.dto.TipFurnizorDto;
import ro.papetti.pluriva.entity.TipFurnizor;
import ro.papetti.pluriva.model.TblTipFurnizorPK;

import java.util.List;
import java.util.Optional;

public interface TipFurnizorService {

    List<TipFurnizorDto> findDtoAll();

    Optional<TipFurnizorDto> findDtoById(int tipFurnizorId);

    List<TipFurnizor> findAll();

    Optional<TipFurnizor> findById(Integer tipFurnizorId);
}
