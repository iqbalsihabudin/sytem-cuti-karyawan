package uas.kel2.sytemcutikaryawan.dto;


import lombok.Data;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;

import java.util.Date;

@Data
public class DetailPengajuanCutiDto {

    private Integer detailPengajuanCutiId;
    private Date tglCuti;
    private PengajuanCuti pengajuanCuti;
    private JenisCuti jenisCuti;
}
