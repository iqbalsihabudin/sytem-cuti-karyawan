package uas.kel2.sytemcutikaryawan.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.Role;
import uas.kel2.sytemcutikaryawan.repo.RoleRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
public class RoleService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private EntityManager entityManager;

    public Iterable<Role> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedRoleFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Role> roles = roleRepo.findAll();
        session.disableFilter("deletedRoleFilter");
        return roles;
    }

    public void remove(Integer id){
        roleRepo.deleteById(id);
    }

    public Role save(Role role){
        if (role.getRoleId() != null){
            Role currentRole = roleRepo.findById(role.getRoleId()).get();
            modelMapper.map(role, currentRole);
            role = currentRole;
        }
        return roleRepo.save(role);
    }
}
