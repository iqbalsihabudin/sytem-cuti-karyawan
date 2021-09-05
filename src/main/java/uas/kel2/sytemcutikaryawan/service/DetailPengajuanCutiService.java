package uas.kel2.sytemcutikaryawan.service;


import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.models.StatusCuti;
import uas.kel2.sytemcutikaryawan.repo.DetailPengajuanCutiRepo;
import uas.kel2.sytemcutikaryawan.repo.PengajuanCutiRepo;
import uas.kel2.sytemcutikaryawan.repo.StatusCutiRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DetailPengajuanCutiService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PengajuanCutiRepo pengajuanCutiRepo;

    @Autowired
    private DetailPengajuanCutiRepo detailPengajuanCutiRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StatusCutiRepo statusCutiRepo;

    public Iterable<DetailPengajuanCuti> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedDetPengajuanFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<DetailPengajuanCuti> detailPengajuanCutis = detailPengajuanCutiRepo.findAll();
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

    public Iterable<DetailPengajuanCuti> findAllByLimit(int start, int limit){return detailPengajuanCutiRepo.findAllByLimit(start,limit);}

    public void pengajuanCancel(DetailPengajuanCuti dp){
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.getById(dp.getPengajuanCuti().getPengajuanCutiId());
        StatusCuti statusCuti = statusCutiRepo.findStatusCutiByStatusCutiId(5);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCutiRepo.save(pengajuanCuti);
    }
}
