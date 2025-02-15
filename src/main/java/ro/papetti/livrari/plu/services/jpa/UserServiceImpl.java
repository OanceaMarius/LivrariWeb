package ro.papetti.livrari.plu.services.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.UserRepozitory;
import ro.papetti.livrari.plu.services.UserService;
import ro.papetti.pluriva.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class UserServiceImpl extends BaseServiceImpl<User, UserRepozitory> implements UserService {
    public UserServiceImpl(UserRepozitory repozitory) {
        super(repozitory);
    }


    @Override
    public <T> List<T> findDTOAll(Class<T> type) {
        return rep.findDTOAll(type);
    }

    @Override
    @Cacheable("Users")
    public <T> List<T> findDTOAllCache(Class<T> type) {
        return findDTOAll(type);
    }

    @Override
    public <T>Optional<T> findDTOById(int userId, Class<T> type) {
        return rep.findDTOById(userId, type);
    }

    @Override
    @Cacheable("UserDTO")
    public <T> Optional<T> findDTOByIdCache(int userId, Class<T> type) {
        return findDTOById(userId, type);
    }


}
