package uas.kel2.sytemcutikaryawan.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;

@Entity
@Table(name = "tbl_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tbl_role SET is_deleted = true WHERE role_id = ?")
@FilterDef(name = "deletedRoleFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedRoleFilter", condition = "is_deleted = :isDeleted")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "name_role",length = 50)
    private String nameRole;
    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;
}
