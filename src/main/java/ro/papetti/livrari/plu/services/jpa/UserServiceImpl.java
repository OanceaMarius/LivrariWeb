package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.UserRepozitory;
import ro.papetti.livrari.plu.services.UserService;
import ro.papetti.pluriva.dto.UserDto;
import ro.papetti.pluriva.entity.User;
import ro.papetti.pluriva.mapstruct.UserMapStruct;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("plurivaTransactionManager")
public class UserServiceImpl extends BaseServiceImpl<User, UserRepozitory> implements UserService {
    public UserServiceImpl(UserRepozitory repozitory) {
        super(repozitory);
    }

    @Autowired
    private UserMapStruct userMapStruct;

    @Override
    public <T> List<T> findDTOIAll(Class<T> type) {
        return rep.findDTOIAll(type);
    }


    @Override
    public <T>Optional<T> findDTOIById(int userId, Class<T> type) {
        return rep.findDTOIById(userId, type);
    }

    @Cacheable(cacheNames = CacheName.USER_DTO, key = "#userId", condition = "#userId != null")
    @Override
    public Optional<UserDto>findDtoById(Integer userId){
        if (userId==null)
            return Optional.empty();
        return rep.findById(userId).map(value->userMapStruct.toDto(value));
    }

    @Override
    public List<UserDto> findDtoAll(){
        return userMapStruct.toDtoList(rep.findAll());
    }

}
