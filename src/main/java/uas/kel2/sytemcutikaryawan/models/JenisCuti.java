package uas.kel2.sytemcutikaryawan.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "tbl_jenis_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tbl_jenis_cuti SET is_deleted = true WHERE jenis_cuti_id = ?")
@FilterDef(name = "deletedJenisCutiFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedJenisCutiFilter", condition = "is_deleted = :isDeleted")
public class JenisCuti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jenisCutiId;
    @Column(name = "jenis_cuti",length = 18)
    private String jenisCuti;
    @Column(name = "deskripsi")
    private String deskripsi;
    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;
}
