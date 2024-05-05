package one.tsv.medclinic.repository;

import one.tsv.medclinic.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

}
