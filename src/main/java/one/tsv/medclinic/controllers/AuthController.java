package one.tsv.medclinic.controllers;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import one.tsv.medclinic.dto.UserDto;
import one.tsv.medclinic.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/app";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/app";
        }
        return "registration";
    }

    @PostMapping("/register")
    public RedirectView registerUserAccount(@Valid final UserDto accountDto) {
        try {
            userService.registerNewUserAccount(accountDto);
        } catch (BadCredentialsException | EntityExistsException e) {
            return new RedirectView("/register?error=" + e.getMessage());
        }
        return new RedirectView("/login?register=true");
    }

}
