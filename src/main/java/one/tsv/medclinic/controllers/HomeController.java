package one.tsv.medclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/app")
    public String app() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "index";
    }
}
