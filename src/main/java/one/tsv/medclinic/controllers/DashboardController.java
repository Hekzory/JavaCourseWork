package one.tsv.medclinic.controllers;

import one.tsv.medclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/app")
    public String app() {
        return "main-dashboard";
    }

    @GetMapping("/app/card")
    public String medicalCard() {
        return "main-dashboard";
    }

    @GetMapping("/app/appointment")
    public String appointment() {
        return "main-dashboard";
    }


}
