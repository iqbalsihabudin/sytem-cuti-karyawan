package uas.kel2.sytemcutikaryawan.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.repo.HakCutiRepo;
import uas.kel2.sytemcutikaryawan.repo.PengajuanCutiRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
public class PengajuanCutiService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PengajuanCutiRepo pengajuanCutiRepo;

    @Autowired
    private EntityManager entityManager;

    public Iterable<PengajuanCuti> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedPengajuanCutiFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<PengajuanCuti> pengajuanCutis = pengajuanCutiRepo.findAll();
        session.disableFilter("deletedLiburFilter");
        return pengajuanCutis;
    }

    public void remove(Integer id){
        pengajuanCutiRepo.deleteById(id);
    }

    public Iterable<PengajuanCuti> findALl(){
        return pengajuanCutiRepo.findAll();
    }

    public PengajuanCuti save(PengajuanCuti pengajuanCuti){
        if (pengajuanCuti.getPengajuanCutiId() != null){
            PengajuanCuti currentPengajuanCuti = pengajuanCutiRepo.findById(pengajuanCuti.getPengajuanCutiId()).get();
            modelMapper.map(pengajuanCuti, currentPengajuanCuti);
//            currentLibur.set(category.getName());
            pengajuanCuti = currentPengajuanCuti;
        }
        return pengajuanCutiRepo.save(pengajuanCuti);
    }

    public Integer PengajuanCutiCountOpen(){
        return pengajuanCutiRepo.pengajuanCutiCount();
    }
}
