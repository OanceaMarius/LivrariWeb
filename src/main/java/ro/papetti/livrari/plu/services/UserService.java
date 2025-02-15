package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
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
     <T> List<T> findDTOAll(Class<T> type);

     <T >List<T> findDTOAllCache(Class<T> type);

    /**
     *
     * @param userId id
     * @param type   e DTO.class
     * @return user-ul in structura DTO
     * @param <T> poate folosi orice DTO finca e parametru generic
     */
     <T>Optional<T> findDTOById(int userId, Class<T> type);

     <T> Optional<T> findDTOByIdCache(int userId, Class<T> type);

}
