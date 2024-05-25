package one.tsv.medclinic.service;

import lombok.AllArgsConstructor;
import one.tsv.medclinic.dto.DoctorDto;
import one.tsv.medclinic.entity.Doctor;
import one.tsv.medclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void saveByDto(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setDegree(doctorDto.getDegree());
        doctor.setSpecialty(doctorDto.getSpecialty());
        doctor.setFullName(doctorDto.getFullName());
        doctorRepository.save(doctor);
    }
}
