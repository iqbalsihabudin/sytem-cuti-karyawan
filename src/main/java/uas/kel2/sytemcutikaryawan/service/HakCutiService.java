package uas.kel2.sytemcutikaryawan.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.repo.HakCutiRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
public class HakCutiService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private HakCutiRepo hakCutiRepo;

    @Autowired
    private EntityManager entityManager;

    public Iterable<HakCuti> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedHakCutiFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<HakCuti> hakCutis = hakCutiRepo.findAll();
        session.disableFilter("deletedHakCutiFilter");
        return hakCutis;
    }

    public void remove(Integer id){
        hakCutiRepo.deleteById(id);
    }

    public HakCuti save(HakCuti hakCuti){
        if (hakCuti.getHakCutiId() != null){
            HakCuti currentHakCuti = hakCutiRepo.findById(hakCuti.getHakCutiId()).get();
            modelMapper.map(hakCuti, currentHakCuti);
//            currentLibur.set(category.getName());
            hakCuti = currentHakCuti;
        }
        return hakCutiRepo.save(hakCuti);
    }
}
