package one.tsv.medclinic.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import one.tsv.medclinic.entity.Appointment;
import one.tsv.medclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointmentListByUserId(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }

    public boolean deleteAppointment(Long userId, Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findByUserIdAndId(userId, appointmentId);
        if (appointmentOptional.isPresent()) {
            appointmentRepository.delete(appointmentOptional.get());
            return true;
        }
        return false;
    }
}
