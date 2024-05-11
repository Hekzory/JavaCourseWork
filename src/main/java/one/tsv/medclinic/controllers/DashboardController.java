package one.tsv.medclinic.controllers;

import one.tsv.medclinic.dto.MedicalCardDto;
import one.tsv.medclinic.entity.Appointment;
import one.tsv.medclinic.entity.MedicalCard;
import one.tsv.medclinic.entity.User;
import one.tsv.medclinic.service.AppointmentService;
import one.tsv.medclinic.service.MedicalCardService;
import one.tsv.medclinic.service.UserService;
import one.tsv.medclinic.util.IdData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashboardController {

    private final UserService userService;
    private final MedicalCardService medicalCardService;

    private final AppointmentService appointmentService;

    public DashboardController(UserService userService, MedicalCardService medicalCardService, AppointmentService appointmentService) {
        this.userService = userService;
        this.medicalCardService = medicalCardService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/app")
    public ModelAndView app(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getByUsername(userDetails.getUsername());
        MedicalCard currentCard = medicalCardService.getByUser(currentUser);
        List<Appointment> appointments = appointmentService.getAppointmentListByUserId(currentUser.getId());

        ModelAndView modelAndView = new ModelAndView("main-dashboard");
        modelAndView.addObject("medicalCard", currentCard);
        modelAndView.addObject("appointments", appointments);
        return modelAndView;
    }

    @GetMapping("/app/card")
    public ModelAndView showMedicalCard(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getByUsername(userDetails.getUsername());
        MedicalCard currentCard = medicalCardService.getByUser(currentUser);


        ModelAndView modelAndView = new ModelAndView("medcard-dashboard");
        modelAndView.addObject("medicalCard", currentCard);
        return modelAndView;
    }

    @PostMapping("/app/card")
    public ModelAndView updateMedicalCard(@ModelAttribute MedicalCardDto newMedicalCardDetails, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getByUsername(userDetails.getUsername());
        MedicalCard currentCard = medicalCardService.getByUser(currentUser);
        medicalCardService.updateCard(currentCard, newMedicalCardDetails);

        ModelAndView modelAndView = new ModelAndView("medcard-dashboard");
        modelAndView.addObject("success", "Мед. карта успешно обновлена!");
        modelAndView.addObject("medicalCard", newMedicalCardDetails);
        return modelAndView;
    }

    @GetMapping("/app/appointment")
    public ModelAndView appointment(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getByUsername(userDetails.getUsername());
        List<Appointment> appointments = appointmentService.getAppointmentListByUserId(currentUser.getId());

        ModelAndView modelAndView = new ModelAndView("appointment-dashboard");
        modelAndView.addObject("appointments", appointments);
        return modelAndView;
    }

    @DeleteMapping("/app/appointment")
    public ResponseEntity<?> deleteAppointment(@AuthenticationPrincipal UserDetails userDetails, @RequestBody IdData id) {
        User currentUser = userService.getByUsername(userDetails.getUsername());
        boolean success = appointmentService.deleteAppointment(currentUser.getId(), id.getId());

        if (success) {
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Appointment not found for current User\"}");
        }
    }

}
