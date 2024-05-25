package one.tsv.medclinic.repository;

import one.tsv.medclinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);

    List<Appointment> findByUserIdAndAppointmentWindow_DateAfter(Long userId, Date date);

    Optional<Appointment> findByUserIdAndId(Long userId, Long id);
}
