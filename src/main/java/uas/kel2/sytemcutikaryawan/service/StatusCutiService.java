package uas.kel2.sytemcutikaryawan.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.models.StatusCuti;
import uas.kel2.sytemcutikaryawan.repo.StatusCutiRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatusCutiService {
    @Autowired
    StatusCutiRepo statusCutiRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private EntityManager entityManager;

    public Iterable<StatusCuti> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedStatusCutiFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<StatusCuti> statusCutis = statusCutiRepo.findAll();
        session.disableFilter("deletedStatusCutiFilter");
        return statusCutis;
    }

    public void remove(Integer id){
        statusCutiRepo.deleteById(id);
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
