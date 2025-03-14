package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.entity.LimitePartenerClient;

import java.util.List;
import java.util.Optional;

public interface LimitePartenerClientService extends BaseService<LimitePartenerClient, Integer> {

    List<LimitePartenerClient> findEagerAll();

    Optional<LimitePartenerClient> findEagerByIdWithDivizie_1(int partenerFirmaId, int partenerId);

    Optional<LimitePartenerClient> findEagerByClientIdWithDivizie_1(int firmaId, int clientId);

    boolean isBlocat( int firmaId, int clientId);
}
