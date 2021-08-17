package uas.kel2.sytemcutikaryawan.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_detailPengajualCuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailPengajuanCuti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailPengajuanCutiId;
    @Column(name = "tgl_cuti")
    private Date tglCuti;

    @OneToOne
    @JoinColumn(name = "pengajuan_cuti_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PengajuanCuti pengajuanCuti;

    @ManyToOne
    @JoinColumn(name = "jenis_cuti_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private JenisCuti jenisCuti;
}
