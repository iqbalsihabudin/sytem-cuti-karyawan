package uas.kel2.sytemcutikaryawan.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_jenis_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JenisCuti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jenisCutiId;
    @Column(name = "jenis_cuti",length = 18)
    private String jenisCuti;
    @Column(name = "deskripsi",length = 100)
    private String deskripsi;
    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;
}
