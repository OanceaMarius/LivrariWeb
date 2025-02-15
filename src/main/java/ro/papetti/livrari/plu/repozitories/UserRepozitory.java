package ro.papetti.livrari.plu.repozitories;


import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.papetti.pluriva.dto.UserDTOI;
import ro.papetti.pluriva.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface UserRepozitory extends JpaRepository<User, Integer> {


    @Query("FROM User u where u.userId =:userId")
    <T> Optional<T> findDTOById(int userId,Class<T> type );

    @Query(value = "FROM User u ")
    <T> List <T> findDTOAll(Class<T> type);
}
