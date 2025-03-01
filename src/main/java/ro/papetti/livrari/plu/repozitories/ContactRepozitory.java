package ro.papetti.livrari.plu.repozitories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.papetti.pluriva.entity.Contact;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface  ContactRepozitory extends JpaRepository<Contact, Integer> {

    @Query("select c from Contact c where c.contactId = :contactId")
    @EntityGraph(value = "Contact.complet")
    Optional<Contact> findEagerById(@Param("contactId")int contactId);

    @Query("select c from Contact c")
    @EntityGraph(value = "Contact.complet")
    List<Contact> findEagerAll();


    @Query(value = "select c from Contact c where c.unitateId = :unitateId")
    public Set<Contact> findContacteByUnitateId(@Param("unitateId") int unitateId);
}
