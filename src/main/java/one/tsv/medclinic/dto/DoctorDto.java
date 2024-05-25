package one.tsv.medclinic.dto;

import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DoctorDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Null
    private String fullName;

    @Null
    private String specialty;

    @Null
    private String degree;
}
