package ro.papetti.livrari.liv.services.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.entity.CoordonateFixe;
import ro.papetti.livrari.liv.repozitories.CoordonateFixeRepozitory;
import ro.papetti.livrari.liv.services.CoordonateFixeService;
import ro.papetti.livrari.model.BaseServiceImpl;

@Service
@Transactional("livrariTransactionManager")
public class CoordonateFixeServiceImpl extends BaseServiceImpl<CoordonateFixe, CoordonateFixeRepozitory> implements CoordonateFixeService {
    public CoordonateFixeServiceImpl(CoordonateFixeRepozitory repozitory) {
        super(repozitory);
    }


}
