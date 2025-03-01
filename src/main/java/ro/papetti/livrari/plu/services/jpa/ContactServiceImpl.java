package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.ContactRepozitory;
import ro.papetti.livrari.plu.services.CompletareDtoService;
import ro.papetti.livrari.plu.services.ContactService;
import ro.papetti.pluriva.dto.ContactDto;
import ro.papetti.pluriva.entity.Contact;
import ro.papetti.pluriva.mapstruct.ContactMapStruct;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
@Transactional("plurivaTransactionManager")
public class ContactServiceImpl extends BaseServiceImpl<Contact, ContactRepozitory> implements ContactService {
    public ContactServiceImpl(ContactRepozitory repozitory) {
        super(repozitory);
    }
    @Autowired
    private ContactMapStruct contactMapStruct;
    @Autowired
    private CompletareDtoService completareDtoService;

    @Override
    public Optional<Contact> findEagerById(Integer contactId) {
        return rep.findEagerById(contactId);
    }

    @Override
    public List<Contact> findEagerAll(){
        return rep.findEagerAll();
    }

    @Override
    @Cacheable(cacheNames = CacheName.CONTACT,key = "#contactId")
    public Optional<ContactDto> findDtoById(int contactId){
        Optional<Contact>optionalContact = findById(contactId);
        Optional<ContactDto> contactDto =optionalContact.map(value->contactMapStruct.toDto(value));
        if (contactDto.isPresent()){
            contactDto.get().setUserModificareDto(completareDtoService.getUserDtoById(contactDto.get().getUserModificareId()));
            contactDto.get().setUserCreareDto(completareDtoService.getUserDtoById(contactDto.get().getUserCreareId()));
        }

        return contactDto;
    }

    @Override
    public Set<Contact> findContacteByUnitateId(int unitateId){
        return rep.findContacteByUnitateId(unitateId);
    }


    @Override
    public Set<ContactDto>findContacteDtoByUnitateId(int unitateId){
        Set<Contact> contactSet = rep.findContacteByUnitateId(unitateId);
        Set<ContactDto> contactDtoSet = Set.copyOf(contactMapStruct.toDtoList(List.copyOf(contactSet)));
        for (ContactDto contactDto:contactDtoSet){
            contactDto.setUserCreareDto(completareDtoService.getUserDtoById(contactDto.getUserCreareId()));
            contactDto.setUserModificareDto(completareDtoService.getUserDtoById(contactDto.getUserModificareId()));
        }
        return contactDtoSet;
    }


    @Override
    public List<ContactDto> findDtoAll(){
        List<Contact> contactList =  findAll();
        List<ContactDto> contactDtoList = contactMapStruct.toDtoList(contactList);
        for (ContactDto dto:contactDtoList){
            dto.setUserCreareDto(completareDtoService.getUserDtoById(dto.getUserCreareId()));
            dto.setUserModificareDto(completareDtoService.getUserDtoById(dto.getUserModificareId()));
        }
        return contactDtoList;
    }
}
