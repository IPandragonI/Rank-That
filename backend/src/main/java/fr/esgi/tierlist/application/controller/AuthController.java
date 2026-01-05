package fr.esgi.tierlist.application.controller;

import fr.esgi.tierlist.application.form.LoginForm;
import fr.esgi.tierlist.application.form.UserForm;
import fr.esgi.tierlist.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String authenticateUser(@RequestBody LoginForm user) {
        return authService.authenticate(user.getUsername(), user.getPassword());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody UserForm userForm) {
        return authService.register(userForm);
    }
}
