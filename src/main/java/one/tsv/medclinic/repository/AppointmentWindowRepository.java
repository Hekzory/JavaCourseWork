package one.tsv.medclinic.repository;

import one.tsv.medclinic.entity.AppointmentWindow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentWindowRepository extends JpaRepository<AppointmentWindow, Long> {
    List<AppointmentWindow> findByDateAfter(Date date);
}
