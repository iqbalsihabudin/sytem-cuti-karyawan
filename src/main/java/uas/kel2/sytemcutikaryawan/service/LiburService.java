package uas.kel2.sytemcutikaryawan.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.repo.LiburRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
public class LiburService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private LiburRepo liburRepo;

    @Autowired
    private EntityManager entityManager;

    public Iterable<Libur> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedLiburFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Libur> liburs = liburRepo.findAll();
        session.disableFilter("deletedLiburFilter");
        return liburs;
    }

    public void remove(Integer id){
        liburRepo.deleteById(id);
    }

    public Libur save(Libur libur){
        if (libur.getLiburId() != null){
            Libur currentLibur = liburRepo.findById(libur.getLiburId()).get();
            modelMapper.map(libur, currentLibur);
            libur = currentLibur;
        }
        return liburRepo.save(libur);
    }

    public Libur findById(Integer id){
        return liburRepo.findByLiburId(id);
    }


}
