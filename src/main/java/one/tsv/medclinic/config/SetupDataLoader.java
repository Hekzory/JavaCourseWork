package one.tsv.medclinic.config;

import one.tsv.medclinic.entity.Role;
import one.tsv.medclinic.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    boolean setupComplete;

    public SetupDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (setupComplete)
            return;
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        setupComplete = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Optional<Role> role = roleRepository.findById(name);
        if (role.isEmpty()) {
            role = Optional.of(new Role(name));
            roleRepository.save(role.get());
        }
        return role.get();
    }
}
