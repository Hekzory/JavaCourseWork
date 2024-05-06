package one.tsv.medclinic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentWindow {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @OneToMany(mappedBy = "appointmentWindow")
    private List<Appointment> appointmentList;
}
