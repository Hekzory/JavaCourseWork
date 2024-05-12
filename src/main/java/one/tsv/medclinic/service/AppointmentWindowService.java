package one.tsv.medclinic.service;

import one.tsv.medclinic.entity.AppointmentWindow;
import one.tsv.medclinic.repository.AppointmentWindowRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentWindowService {
    private final AppointmentWindowRepository appointmentWindowRepository;

    public AppointmentWindowService(AppointmentWindowRepository appointmentWindowRepository) {
        this.appointmentWindowRepository = appointmentWindowRepository;
    }

    public List<AppointmentWindow> findByDateAfter(Date date) {
        return appointmentWindowRepository.findByDateAfter(date);
    }
}
