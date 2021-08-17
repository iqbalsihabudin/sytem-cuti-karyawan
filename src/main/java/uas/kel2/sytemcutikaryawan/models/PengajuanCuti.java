package uas.kel2.sytemcutikaryawan.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pengajuan_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PengajuanCuti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pengajuanCutiId;
    @Column(name = "pengganti_id",length = 18)
    private Integer penggantiId;
    @Column(name = "hrd_id",length = 18)
    private Integer hrdId;
    @Column(name = "alamat",length = 18)
    private String alamat;
    @Column(name = "no_telp",length = 100)
    private String noTelp;
    @Column(name = "keterangan",length = 100)
    private String keterangan;
    @Column(name = "lama_cuti",length = 100)
    private Integer lamaCuti;
    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "status_cuti_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StatusCuti statusCuti;
}
