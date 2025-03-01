package ro.papetti.livrari.liv.services.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.LivrariTabele.entity.Masina;
import ro.papetti.livrari.liv.repozitories.MasinaRepozitory;
import ro.papetti.livrari.liv.services.MasinaService;
import ro.papetti.livrari.model.BaseServiceImpl;

@Service
@Transactional("livrariTransactionManager")
public class MasinaServiceImpl extends BaseServiceImpl<Masina, MasinaRepozitory>implements MasinaService {
    public MasinaServiceImpl(MasinaRepozitory repozitory) {
        super(repozitory);
    }
}
