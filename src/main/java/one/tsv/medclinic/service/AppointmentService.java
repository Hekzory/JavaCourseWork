package one.tsv.medclinic.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import one.tsv.medclinic.entity.Appointment;
import one.tsv.medclinic.entity.AppointmentWindow;
import one.tsv.medclinic.entity.User;
import one.tsv.medclinic.repository.AppointmentRepository;
import one.tsv.medclinic.repository.AppointmentWindowRepository;
import one.tsv.medclinic.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentWindowRepository appointmentWindowRepository;
    private final UserRepository userRepository;

    public List<Appointment> getAppointmentListByUserId(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }

    public List<Appointment> getAppointmentListByUserIdAndDateAfter(Long userId, Date date) {
        return appointmentRepository.findByUserIdAndAppointmentWindow_DateAfter(userId, date);
    }

    public boolean deleteAppointment(Long userId, Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findByUserIdAndId(userId, appointmentId);
        if (appointmentOptional.isPresent()) {
            appointmentRepository.delete(appointmentOptional.get());
            return true;
        }
        return false;
    }

    public boolean makeAppointment(Long userId, Long appointmentWindowId) {
        Optional<AppointmentWindow> window = appointmentWindowRepository.findById(appointmentWindowId);
        Optional<User> user = userRepository.findById(userId);
        if (window.isPresent()) {
            Appointment appointment = new Appointment();
            appointment.setAppointmentWindow(window.get());
            appointment.setUser(user.get());
            appointment.setAppointmentNumber(1L);
            appointmentRepository.save(appointment);
            return true;
        }
        return false;
    }
}
