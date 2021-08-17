package uas.kel2.sytemcutikaryawan.dto;

import lombok.Data;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.StatusCuti;

@Data
public class PengajuanCutiDto {
    private Integer pengajuanCutiId;
    private Integer penggantiId;
    private Integer hrdId;
    private String alamat;
    private String noTelp;
    private String keterangan;
    private Integer lamaCuti;
    private Employee employee;
    private StatusCuti statusCuti;
}
