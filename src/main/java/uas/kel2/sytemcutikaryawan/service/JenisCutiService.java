package uas.kel2.sytemcutikaryawan.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.repo.JenisCutiRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JenisCutiService {
    @Autowired
    JenisCutiRepo jenisCutiRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private EntityManager entityManager;

    public Iterable<JenisCuti> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedJenisCutiFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<JenisCuti> jenisCutis = jenisCutiRepo.findAll();
        session.disableFilter("deletedJenisCutiFilter");
        return jenisCutis;
    }

    public void remove(Integer id){
        jenisCutiRepo.deleteById(id);
    }

    public JenisCuti save(JenisCuti jenisCuti){
        if(jenisCuti.getJenisCutiId()!=null){
            JenisCuti currentJenisCuti = jenisCutiRepo.findById(jenisCuti.getJenisCutiId()).get();
            modelMapper.map(jenisCuti, currentJenisCuti);
            jenisCuti = currentJenisCuti;
        }
        return jenisCutiRepo.save(jenisCuti);
    }

    public JenisCuti findById(Integer id){
        return jenisCutiRepo.findByJenisCutiId(id);
    }
}
