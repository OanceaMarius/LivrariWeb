package ro.papetti.livrari.plu.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.livrari.configs.cache.CacheName;
import ro.papetti.livrari.model.BaseServiceImpl;
import ro.papetti.livrari.plu.repozitories.WorkingHoursRepository;
import ro.papetti.livrari.plu.services.WorkingHoursService;
import ro.papetti.pluriva.dto.WorkingHoursDto;
import ro.papetti.pluriva.entity.WorkingHours;
import ro.papetti.pluriva.mapstruct.WorkingHoursMapStruct;

import java.util.List;
import java.util.Optional;
@Service
@Transactional("plurivaTransactionManager")
public class WorkingHoursServiceImpl extends BaseServiceImpl<WorkingHours, WorkingHoursRepository> implements WorkingHoursService {
    public WorkingHoursServiceImpl(WorkingHoursRepository repozitory) {
        super(repozitory);
    }
    @Autowired
    private WorkingHoursMapStruct workingHoursMapStruct;

    @Override
    public Optional<WorkingHours> findEagerById(int workingHoursId){
        return rep.findEagerById(workingHoursId);
    }

    @Override
    public List<WorkingHours> findEagerAll(){
        return rep.findEagerAll();
    }

    @Override
    @Cacheable(cacheNames = CacheName.WORKING_HOURS, key = "#workingHoursId", condition = "#workingHoursId != null")
    public Optional<WorkingHoursDto> findDtoById(int workingHoursId){
        Optional<WorkingHours> optionalWorkingHours =rep.findEagerById(workingHoursId);
        return optionalWorkingHours.map(value->workingHoursMapStruct.toDto(value));
    }

    @Override
    public List<WorkingHoursDto> findDtoEagerAll(){
        return workingHoursMapStruct.toDtoList(rep.findEagerAll());
    }

}
