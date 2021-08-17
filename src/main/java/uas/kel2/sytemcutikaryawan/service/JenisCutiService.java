package uas.kel2.sytemcutikaryawan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.repo.JenisCutiRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JenisCutiService {
    @Autowired
    JenisCutiRepo jenisCutiRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<JenisCuti> findALl() {
        return jenisCutiRepo.findAll();
    }

    public JenisCuti save(JenisCuti jenisCuti){
        if(jenisCuti.getJenisCutiId()!=null){
            JenisCuti currentJenisCuti = jenisCutiRepo.findById(jenisCuti.getJenisCutiId()).get();
            modelMapper.map(jenisCuti, currentJenisCuti);
            jenisCuti = currentJenisCuti;
        }
        return jenisCutiRepo.save(jenisCuti);
    }
}
