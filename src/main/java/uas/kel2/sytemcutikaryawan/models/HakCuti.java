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
@Table(name = "tbl_hak_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tbl_hak_cuti SET is_deleted = true WHERE hak_cuti_id = ?")
@FilterDef(name = "deletedHakCutiFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedHakCutiFilter", condition = "is_deleted = :isDeleted")
public class HakCuti extends BaseEntity<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hakCutiId;
    @Column(name = "sisa_cuti")
    private Integer sisaCuti;
    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "jenis_cuti_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private JenisCuti jenisCuti;

}
