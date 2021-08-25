package uas.kel2.sytemcutikaryawan.utis;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import uas.kel2.sytemcutikaryawan.models.Employee;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Employee currentEmployee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentEmployee.getUsername());
    }
}

