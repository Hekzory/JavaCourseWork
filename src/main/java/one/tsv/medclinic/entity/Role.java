package one.tsv.medclinic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    private String id;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(String id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return id;
    }
}
