package one.tsv.medclinic.controllers;

import one.tsv.medclinic.dto.MedicalCardDto;
import one.tsv.medclinic.entity.MedicalCard;
import one.tsv.medclinic.entity.User;
import one.tsv.medclinic.service.MedicalCardService;
import one.tsv.medclinic.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    private final UserService userService;
    private final MedicalCardService medicalCardService;

    public DashboardController(UserService userService, MedicalCardService medicalCardService) {
        this.userService = userService;
        this.medicalCardService = medicalCardService;
    }

    @GetMapping("/app")
    public ModelAndView app(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getByUsername(userDetails.getUsername());
        MedicalCard currentCard = medicalCardService.getByUser(currentUser);

        ModelAndView modelAndView = new ModelAndView("main-dashboard"); // Assuming 'medicalCardForm' is the name of your template
        modelAndView.addObject("medicalCard", currentCard);
        return modelAndView;
    }

    @GetMapping("/app/card")
    public ModelAndView showMedicalCard(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getByUsername(userDetails.getUsername());
        MedicalCard currentCard = medicalCardService.getByUser(currentUser);

        ModelAndView modelAndView = new ModelAndView("medcard-dashboard"); // Assuming 'medicalCardForm' is the name of your template
        modelAndView.addObject("medicalCard", currentCard);
        return modelAndView;
    }

    @PostMapping("/app/card")
    public ModelAndView updateMedicalCard(@ModelAttribute MedicalCardDto newMedicalCardDetails, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getByUsername(userDetails.getUsername());
        MedicalCard currentCard = medicalCardService.getByUser(currentUser);
        medicalCardService.updateCard(currentCard, newMedicalCardDetails);

        ModelAndView modelAndView = new ModelAndView("medcard-dashboard"); // Assuming 'medicalCardForm' is the name of your template
        modelAndView.addObject("success", "Мед. карта успешно обновлена!");
        modelAndView.addObject("medicalCard", newMedicalCardDetails);
        return modelAndView;
    }

    @GetMapping("/app/appointment")
    public String appointment() {
        return "appointment-dashboard";
    }


}
