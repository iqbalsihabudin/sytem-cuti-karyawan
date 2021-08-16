package uas.kel2.sytemcutikaryawan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.repo.LiburRepo;

import javax.transaction.Transactional;

@Service
@Transactional
public class LiburService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private LiburRepo liburRepo;

    public Iterable<Libur> findALl(){
        return liburRepo.findAll();
    }

    public Libur save(Libur libur){
        if (libur.getLiburId() != null){
            Libur currentLibur = liburRepo.findById(libur.getLiburId()).get();
            modelMapper.map(libur, currentLibur);
//            currentLibur.set(category.getName());
            libur = currentLibur;
        }
        return liburRepo.save(libur);
    }


}
