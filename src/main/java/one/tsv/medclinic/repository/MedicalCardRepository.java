package one.tsv.medclinic.repository;

import one.tsv.medclinic.entity.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {
    MedicalCard findByUserId(Long userId);
}
