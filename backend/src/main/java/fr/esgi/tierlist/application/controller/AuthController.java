package fr.esgi.tierlist.application.controller;

import fr.esgi.tierlist.application.dto.AuthDto;
import fr.esgi.tierlist.application.dto.UserDto;
import fr.esgi.tierlist.application.form.LoginForm;
import fr.esgi.tierlist.application.form.UserForm;
import fr.esgi.tierlist.domain.model.User;
import fr.esgi.tierlist.domain.service.AuthService;
import fr.esgi.tierlist.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthDto authenticateUser(@RequestBody LoginForm loginForm) {
        String token = authService.authenticate(loginForm.getUsername(), loginForm.getPassword());
        if (token != null) {
            User user = userService.findByUsername(loginForm.getUsername());
            return new AuthDto(token, UserDto.transfer(user));
        } else {
            throw new RuntimeException("Error: Invalid username or password");
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthDto registerUser(@RequestBody UserForm userForm) {
        String token = authService.register(userForm);
        User user = userService.findByUsername(userForm.getUsername());
        return new AuthDto(token, UserDto.transfer(user));
    }
}
