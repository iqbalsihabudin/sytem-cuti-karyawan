package uas.kel2.sytemcutikaryawan.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;
import uas.kel2.sytemcutikaryawan.repo.DetailPengajuanCutiRepo;

import javax.transaction.Transactional;

@Service
@Transactional
public class DetailPengajuanCutiService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private DetailPengajuanCutiRepo detailPengajuanCutiRepo;

    public Iterable<DetailPengajuanCuti> findALl(){
        return detailPengajuanCutiRepo.findAll();
    }

    public DetailPengajuanCuti save(DetailPengajuanCuti detailPengajuanCuti){
        if (detailPengajuanCuti.getDetailPengajuanCutiId() != null){
            DetailPengajuanCuti currentDetailPengajuanCuti = detailPengajuanCutiRepo.findById(detailPengajuanCuti.getDetailPengajuanCutiId()).get();
            modelMapper.map(detailPengajuanCuti, currentDetailPengajuanCuti);
//            currentLibur.set(category.getName());
            detailPengajuanCuti = currentDetailPengajuanCuti;
        }
        return detailPengajuanCutiRepo.save(detailPengajuanCuti);
    }
}
