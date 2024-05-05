package one.tsv.medclinic.controllers;

import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import one.tsv.medclinic.dto.UserDto;
import one.tsv.medclinic.entity.User;
import one.tsv.medclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "registration";
    }

    @PostMapping("/register")
    public RedirectView registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request) {
        try {
            final User registered = userService.registerNewUserAccount(accountDto);
        }
        catch (BadCredentialsException | EntityExistsException e) {
            return new RedirectView("/register?error=" + e.getMessage());
        }
        return new RedirectView("/login?register=true");
    }

}
