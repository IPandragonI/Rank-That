package fr.esgi.tierlist.application.controller;

import fr.esgi.tierlist.application.dto.UserDto;
import fr.esgi.tierlist.application.form.UserForm;
import fr.esgi.tierlist.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto findTierListById(@PathVariable Long id) {
        return UserDto.transfer(userService.findById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateTierList(@PathVariable Long id, @RequestBody UserForm userForm) {
        return UserDto.transfer(userService.update(id, userForm));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTierList(@PathVariable Long id) {
        userService.delete(id);
    }

}
