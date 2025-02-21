package ro.papetti.livrari.plu.services;

import org.springframework.cache.annotation.Cacheable;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.UserDto;
import ro.papetti.pluriva.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User, Integer> {
    /**
     *
     * @param type  e DTO.class
     * @return lista cu toate inregistrarile in structura T (DTO)
     * @param <T> poate folosi orice DTO finca e parametru generic
     */
     <T> List<T> findDTOIAll(Class<T> type);



    /**
     *
     * @param userId id
     * @param type   e DTO.class
     * @return user-ul in structura DTO
     * @param <T> poate folosi orice DTO finca e parametru generic
     */
     <T>Optional<T> findDTOIById(int userId, Class<T> type);


    Optional<UserDto>findDtoById(int userId);

    List<UserDto> findDtoAll();
}
