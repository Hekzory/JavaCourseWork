package one.tsv.medclinic.controllers;

import lombok.AllArgsConstructor;
import one.tsv.medclinic.dto.DoctorDto;
import one.tsv.medclinic.service.DoctorService;
import one.tsv.medclinic.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    private final DoctorService doctorService;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/admin/doctor")
    public RedirectView updateMedicalCard(@ModelAttribute DoctorDto doctorDetails, @AuthenticationPrincipal UserDetails userDetails) {
        doctorService.saveByDto(doctorDetails);
        return new RedirectView("/admin?success=doctor");
    }
}
