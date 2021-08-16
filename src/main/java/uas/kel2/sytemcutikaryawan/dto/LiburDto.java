package uas.kel2.sytemcutikaryawan.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LiburDto {
    private Integer liburId;
    private String namaLibur;
    private String deskripsi;
    private Date tglLibur;
}
