package one.tsv.medclinic.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import one.tsv.medclinic.entity.AppointmentWindow;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentWindowDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Date date;

    private Long queueLength;

    private String doctorName;

    private String doctorSpecialty;

    @Null
    private Long id;

    public static AppointmentWindowDto fromEntity(AppointmentWindow appointmentWindow) {
        if (appointmentWindow == null) {
            return null;
        }
        return new AppointmentWindowDto(
                appointmentWindow.getDate(),
                appointmentWindow.getQueueLength(),
                appointmentWindow.getDoctor().getFullName(),
                appointmentWindow.getDoctor().getSpecialty(),
                appointmentWindow.getId()
        );
    }
}
