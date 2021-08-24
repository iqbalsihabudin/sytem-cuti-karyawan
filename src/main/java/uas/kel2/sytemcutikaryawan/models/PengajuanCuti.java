package uas.kel2.sytemcutikaryawan.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pengajuan_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tbl_pengajuan_cuti SET is_deleted = true WHERE pengajuan_cuti_id = ?")
@FilterDef(name = "deletedPengajuanCutiFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedPengajuanCutiFilter", condition = "is_deleted = :isDeleted")
public class PengajuanCuti extends BaseEntity<String>{
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
