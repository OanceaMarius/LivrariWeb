package ro.papetti.livrari.plu.services;

import ro.papetti.livrari.model.BaseService;
import ro.papetti.pluriva.dto.ContactDto;
import ro.papetti.pluriva.entity.Contact;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ContactService extends BaseService<Contact, Integer> {


    Optional<Contact> findEagerById(Integer contactId);

    List<Contact> findEagerAll();

    Optional<ContactDto> findDtoById(int contactId);

    Set<Contact> findContacteByUnitateId(int unitateId);

    Set<ContactDto>findContacteDtoByUnitateId(int unitateId);

    List<ContactDto> findDtoAll();
}
