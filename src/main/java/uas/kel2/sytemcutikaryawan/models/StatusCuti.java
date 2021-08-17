package uas.kel2.sytemcutikaryawan.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_status_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusCuti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusCutiId;
    @Column(name = "status_cuti",length = 18)
    private String statusCuti;
    @Column(name = "deskripsi",length = 100)
    private String deskripsi;
    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;
}
