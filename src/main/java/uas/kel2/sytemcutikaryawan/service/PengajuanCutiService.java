package uas.kel2.sytemcutikaryawan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.repo.HakCutiRepo;
import uas.kel2.sytemcutikaryawan.repo.PengajuanCutiRepo;

import javax.transaction.Transactional;

@Service
@Transactional
public class PengajuanCutiService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PengajuanCutiRepo pengajuanCutiRepo;

    public Iterable<PengajuanCuti> findALl(){
        return pengajuanCutiRepo.findAll();
    }
}
