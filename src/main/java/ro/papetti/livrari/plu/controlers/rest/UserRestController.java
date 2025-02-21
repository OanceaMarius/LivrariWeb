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
import ro.papetti.livrari.plu.services.UserService;
import ro.papetti.pluriva.dto.UmDto;
import ro.papetti.pluriva.dto.UserDto;
import ro.papetti.pluriva.entity.Um;
import ro.papetti.pluriva.entity.User;
import ro.papetti.pluriva.mapstruct.UserMapStruct;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class UserRestController {

    private final UserService userService;


    @GetMapping("/User")
    public List<User> findUserAll(){
        return userService.findAll();
    }

    @GetMapping("/UserDTO")
    public List<UserDto> findUserDtoAll(){
        return userService.findDtoAll();
    }

    @GetMapping("/UserDTO/{userId}")
    public ResponseEntity<UserDto> findUmDtoyId(@NonNull @PathVariable int userId){
        UserDto entity = userService.findDtoById(userId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc UserDto cu userId: " + userId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/User/{userId}")
    public ResponseEntity<User> findUmById(@NonNull @PathVariable int userId){
        User entity = userService.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc User cu userId: " + userId));
        return ResponseEntity.ok(entity);
    }
}
