package uas.kel2.sytemcutikaryawan.dto;

import lombok.Data;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;

@Data
public class HakCutiDto {

    private Integer hakCutiId;
    private Integer sisaCuti;
    private JenisCuti jenisCuti;
    private Employee employee;

}
