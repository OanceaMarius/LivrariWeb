package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.papetti.livrari.plu.services.FollowUpService;
import ro.papetti.pluriva.dto.FollowUpDto;
import ro.papetti.pluriva.entity.FollowUp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pluriva")
public class FollowUpRestController {

    private final FollowUpService followUpService;

    //ca sa introduc data in formatul dorit de mine
    @InitBinder
    public void initBinder(@org.jetbrains.annotations.NotNull WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @GetMapping("/FollowUp")
    public List<FollowUp> findFollowUpAll() {
        return followUpService.findAll();
    }


    @GetMapping("/FollowUp/{followupId}")
    public ResponseEntity<FollowUp> findFollowUpById(@NonNull @PathVariable int followupId) {
        FollowUp entity = followUpService.findEagerById(followupId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc FollowUp cu followupId: "+ followupId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/FollowUpDTO")
    public List<FollowUpDto> findFollowUpDtoAll() {
        return followUpService.findDtoAll();
    }


    @GetMapping("/FollowUpDTO/{followupId}")
    public ResponseEntity<FollowUpDto> findFollowUpDtoById(@NonNull @PathVariable int followupId) {
        FollowUpDto entity = followUpService.findDtoById(followupId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc FollowUpDto cu followupId: "+ followupId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/FollowUpDTO/DupaData/{data}")
    public List<FollowUpDto> findFollowUpDtoDupaData(@NonNull @PathVariable Date data) {
        return followUpService.findDtoDataCreareDupa(data);
    }

    @GetMapping("/FollowUpDTO/TipActivitate/{tipActivitate}")
    public List<FollowUpDto> findFollowUpDtoByTipActivitate(@NonNull @PathVariable int  tipActivitate) {
        return followUpService.findDtoByTipActivitate(tipActivitate);
    }

    @GetMapping("/FollowUpDTO/TipActivitateDataCreare/{tipActivitate}/{dataCreare}")
    public List<FollowUpDto> findFollowUpDtoByTipActivitate(@NonNull @PathVariable int  tipActivitate, @NonNull @PathVariable Date dataCreare ) {
        return followUpService.findDtoByTipActivitateSiDataCreareDupa(tipActivitate,dataCreare);
    }

}
