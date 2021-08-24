package uas.kel2.sytemcutikaryawan.models;

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
@Table(name = "tbl_libur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tbl_libur SET is_deleted = true WHERE libur_id = ?")
@FilterDef(name = "deletedLiburFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedLiburFilter", condition = "is_deleted = :isDeleted")
public class Libur extends BaseEntity<String>{
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
