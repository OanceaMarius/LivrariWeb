package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.ContactService;
import ro.papetti.pluriva.dto.BrandDto;
import ro.papetti.pluriva.dto.ContactDto;
import ro.papetti.pluriva.entity.Brand;
import ro.papetti.pluriva.entity.Contact;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class ContactRestController {

    private final ContactService contactService;

    @GetMapping("/Contact")
    public List<Contact> findBrandAll(){
        return contactService.findEagerAll();
    }

    @GetMapping("/ContactDTO")
    public List<ContactDto> findContactDtoAll(){
        return contactService.findDtoAll();
    }

    @GetMapping("/ContactDTO/{contactId}")
    public ResponseEntity<ContactDto> findContactDtoById(@NonNull @PathVariable int contactId){
        ContactDto entity = contactService.findDtoById(contactId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc ContactDto cu contactId: "+contactId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Contact/{contactId}")
    public ResponseEntity<Contact> findContactById(@NonNull @PathVariable int contactId){
        Contact entity = contactService.findEagerById(contactId)
                .orElseThrow(()-> new EntityNotFoundException("Nu gasesc Contact cu contactId: "+contactId));
        return ResponseEntity.ok(entity);
    }

}
