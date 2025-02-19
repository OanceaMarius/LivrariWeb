package ro.papetti.livrari.plu.controlers.rest;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.papetti.livrari.plu.services.BrandService;
import ro.papetti.pluriva.dto.BrandDto;
import ro.papetti.pluriva.dtoi.BrandDTOI;
import ro.papetti.pluriva.entity.Brand;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/pluriva")
public class BrandRestController {
    private final BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/Brand")
    public List<Brand> findBrandAll(){
        return brandService.findAll();
    }

    @GetMapping("/BrandDTO")
    public List<BrandDto> findBrandDtoAll(){
        return brandService.findDtoAll();
    }

    @GetMapping("/BrandDTO/{brandId}")
    public ResponseEntity<BrandDto> findBrandDtoById(@NonNull @PathVariable int brandId){
        BrandDto entity = brandService.findDtoById(brandId)
                .orElseThrow(()->new EntityNotFoundException("Nu gasesc BrandDto cu brandId: "+brandId));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/Brand/{brandId}")
    public ResponseEntity<Brand> findBrandById(@NonNull @PathVariable int brandId){
        Brand entity = brandService.findById(brandId)
                .orElseThrow(()-> new EntityNotFoundException("Nu gasesc Brand cu brandId: "+brandId));
        return ResponseEntity.ok(entity);
    }

}
