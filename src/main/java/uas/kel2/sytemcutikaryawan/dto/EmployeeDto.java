package uas.kel2.sytemcutikaryawan.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class EmployeeDto {
    private Integer employee_id;
    private String nip;
    private String namaLengkap;
    private String divisi;
    private String email;
    private String username;
    private String password;
}
