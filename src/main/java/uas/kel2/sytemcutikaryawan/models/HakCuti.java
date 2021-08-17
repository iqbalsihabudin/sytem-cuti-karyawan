package uas.kel2.sytemcutikaryawan.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_hakCuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HakCuti {
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
