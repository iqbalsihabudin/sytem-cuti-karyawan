package uas.kel2.sytemcutikaryawan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.models.StatusCuti;
import uas.kel2.sytemcutikaryawan.repo.StatusCutiRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatusCutiService {
    @Autowired
    StatusCutiRepo statusCutiRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<StatusCuti> findALl() {
        return statusCutiRepo.findAll();
    }

    public StatusCuti save(StatusCuti statusCuti){
        if(statusCuti.getStatusCutiId()!=null){
            StatusCuti currentStatusCuti = statusCutiRepo.findById(statusCuti.getStatusCutiId()).get();
            modelMapper.map(statusCuti, currentStatusCuti);
            statusCuti = currentStatusCuti;
        }
        return statusCutiRepo.save(statusCuti);
    }
}
