package uas.kel2.sytemcutikaryawan.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.*;
import uas.kel2.sytemcutikaryawan.repo.HakCutiRepo;
import uas.kel2.sytemcutikaryawan.repo.PengajuanCutiRepo;
import uas.kel2.sytemcutikaryawan.repo.StatusCutiRepo;

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
    private StatusCutiRepo statusCutiRepo;

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

    public void updateStatusAcc(Integer id, Integer hrd){
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.getById(id);
        StatusCuti statusCuti = statusCutiRepo.findStatusCutiByStatusCutiId(3);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti.setHrdId(hrd);
        pengajuanCutiRepo.save(pengajuanCuti);
    }

    public void updateStatusReject(Integer id, Integer hrd){
        PengajuanCuti pengajuanCuti = pengajuanCutiRepo.getById(id);
        StatusCuti statusCuti = statusCutiRepo.findStatusCutiByStatusCutiId(4);
        pengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti.setHrdId(hrd);
        pengajuanCutiRepo.save(pengajuanCuti);
    }

    public Iterable<PengajuanCuti> findALl(){
        return pengajuanCutiRepo.findAll();
    }

    public PengajuanCuti save(PengajuanCuti pengajuanCuti){
        if (pengajuanCuti.getPengajuanCutiId() != null){
            PengajuanCuti currentPengajuanCuti = pengajuanCutiRepo.findById(pengajuanCuti.getPengajuanCutiId()).get();
            modelMapper.map(pengajuanCuti, currentPengajuanCuti);
            StatusCuti statusCuti = statusCutiRepo.findStatusCutiByStatusCutiId(2);
            currentPengajuanCuti.setStatusCuti(statusCuti);
            pengajuanCuti = currentPengajuanCuti;
        }else{
            StatusCuti statusCuti = statusCutiRepo.findStatusCutiByStatusCutiId(1);
            pengajuanCuti.setStatusCuti(statusCuti);
        }
        return pengajuanCutiRepo.save(pengajuanCuti);
    }

    public Integer PengajuanCutiCountOpen(){
        return pengajuanCutiRepo.pengajuanCutiCountOpen();
    }

    public Integer PengajuanCutiCountAll(){
        return pengajuanCutiRepo.pengajuanCutiCountAll();
    }

    public PengajuanCuti findById(Integer id){
        return pengajuanCutiRepo.findByPengajuanCutiId(id);
    }

    public PengajuanCuti pengajuanCutiById(int id){
        return pengajuanCutiRepo.pengajuanCutiById(id);
    }

    public Iterable<PengajuanCuti> findAllByLimit(int start, int limit){return pengajuanCutiRepo.findAllByLimit(start,limit);}

    public PengajuanCuti update(PengajuanCuti pengajuanCuti){
        PengajuanCuti currentPengajuanCuti = pengajuanCutiRepo.findById(pengajuanCuti.getPengajuanCutiId()).get();
        modelMapper.map(pengajuanCuti, currentPengajuanCuti);
        StatusCuti statusCuti = statusCutiRepo.findStatusCutiByStatusCutiId(1);
        currentPengajuanCuti.setStatusCuti(statusCuti);
        pengajuanCuti = currentPengajuanCuti;
        return pengajuanCutiRepo.save(pengajuanCuti);
    }
}
