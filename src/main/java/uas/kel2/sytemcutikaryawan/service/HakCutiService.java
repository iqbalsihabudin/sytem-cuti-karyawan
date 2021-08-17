package uas.kel2.sytemcutikaryawan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.repo.HakCutiRepo;

import javax.transaction.Transactional;

@Service
@Transactional
public class HakCutiService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private HakCutiRepo hakCutiRepo;

    public Iterable<HakCuti> findALl(){
        return hakCutiRepo.findAll();
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
