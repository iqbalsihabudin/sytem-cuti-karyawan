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
@Table(name = "tbl_status_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tbl_status_cuti SET is_deleted = true WHERE status_cuti_id = ?")
@FilterDef(name = "deletedStatusCutiFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedStatusCutiFilter", condition = "is_deleted = :isDeleted")
public class StatusCuti extends BaseEntity<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusCutiId;
    @Column(name = "status_cuti",length = 18)
    private String statusCuti;
    @Column(name = "deskripsi")
    private String deskripsi;
    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;
}
