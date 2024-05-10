package one.tsv.medclinic.service;

import one.tsv.medclinic.dto.MedicalCardDto;
import one.tsv.medclinic.entity.MedicalCard;
import one.tsv.medclinic.entity.User;
import one.tsv.medclinic.repository.MedicalCardRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalCardService {

    private final MedicalCardRepository medicalCardRepository;

    public MedicalCardService(MedicalCardRepository medicalCardRepository) {
        this.medicalCardRepository = medicalCardRepository;
    }

    public MedicalCard getByUser(User user) {
        return medicalCardRepository.findByUserId(user.getId());
    }

    public void updateCard(MedicalCard to, MedicalCardDto from) {
        to.setHeight(from.getHeight());
        to.setWeight(from.getWeight());
        to.setBloodType(from.getBloodType());
        to.setOtherInfo(from.getOtherInfo());
        medicalCardRepository.save(to);
    }
}
