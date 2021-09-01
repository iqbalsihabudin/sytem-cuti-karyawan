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
import java.util.Date;

@Entity
@Table(name = "tbl_detail_pengajuan_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tbl_detail_pengajual_cuti SET is_deleted = true WHERE detail_pengajuan_cuti_id = ?")
@FilterDef(name = "deletedDetPengajuanFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedDetPengajuanFilter", condition = "is_deleted = :isDeleted")
public class DetailPengajuanCuti extends BaseEntity<String>{

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

    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;
}
