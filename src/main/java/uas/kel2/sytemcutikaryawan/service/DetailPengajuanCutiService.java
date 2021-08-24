package uas.kel2.sytemcutikaryawan.service;


import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.repo.DetailPengajuanCutiRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
public class DetailPengajuanCutiService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private DetailPengajuanCutiRepo detailPengajuanCutiRepo;

    @Autowired
    private EntityManager entityManager;

    public Iterable<DetailPengajuanCuti> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedDetPengajuanFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<DetailPengajuanCuti> detailPengajuanCutis = detailPengajuanCutiRepo.findAll();
        session.disableFilter("deletedLiburFilter");
        return detailPengajuanCutis;
    }
    public DetailPengajuanCuti save(DetailPengajuanCuti detailPengajuanCuti){
        if (detailPengajuanCuti.getDetailPengajuanCutiId() != null){
            DetailPengajuanCuti currentDetailPengajuanCuti = detailPengajuanCutiRepo.findById(detailPengajuanCuti.getDetailPengajuanCutiId()).get();
            modelMapper.map(detailPengajuanCuti, currentDetailPengajuanCuti);
            detailPengajuanCuti = currentDetailPengajuanCuti;
        }
        return detailPengajuanCutiRepo.save(detailPengajuanCuti);
    }
    public void remove(Integer id){
        detailPengajuanCutiRepo.deleteById(id);
    }

    public DetailPengajuanCuti findById(Integer id){
        return detailPengajuanCutiRepo.findByDetailPengajuanCutiId(id);
    }
}
