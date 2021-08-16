package uas.kel2.sytemcutikaryawan.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_libur")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Libur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libur_id")
    private Integer liburId;
    @Column(name = "nama_libur")
    private String namaLibur;
    @Column(name = "deskripsi")
    private String deskripsi;
    @Column(name = "tgl_libur")
    private Date tglLibur;
    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;

}
