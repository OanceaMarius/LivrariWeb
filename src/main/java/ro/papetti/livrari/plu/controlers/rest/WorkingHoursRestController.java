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
import ro.papetti.livrari.plu.services.WorkingHoursService;
import ro.papetti.pluriva.dto.WorkingHoursDto;
import ro.papetti.pluriva.entity.User;
import ro.papetti.pluriva.entity.WorkingHours;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pluriva")
public class WorkingHoursRestController {

    private final WorkingHoursService workingHoursService;

    @GetMapping("/WorkingHours")
    public List<WorkingHours> findWorkingHoursAll(){
        return workingHoursService.findEagerAll();
    }

    @GetMapping("/WorkingHours/{workingHoursId}")
    public ResponseEntity<WorkingHours> findWorkingHoursEagerById(@NonNull @PathVariable int workingHoursId){
        WorkingHours entity = workingHoursService.findEagerById(workingHoursId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc WorkingHours cu workingHoursId: " + workingHoursId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/WorkingHoursDTO")
    public List<WorkingHoursDto> findWorkingHoursDtoAll(){
        return workingHoursService.findDtoEagerAll();
    }

    @GetMapping("/WorkingHoursDTO/{workingHoursId}")
    public ResponseEntity<WorkingHoursDto> findWorkingHoursDtoEagerById(@NonNull @PathVariable int workingHoursId){
        WorkingHoursDto entity = workingHoursService.findDtoById(workingHoursId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc WorkingHoursDto cu workingHoursId: " + workingHoursId));
        return ResponseEntity.ok(entity);
    }

}
