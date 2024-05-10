package one.tsv.medclinic.dto;

import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class MedicalCardDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Null
    private double height;

    @Null
    private double weight;

    @Null
    private String bloodType;

    @Null
    private String otherInfo;
}
