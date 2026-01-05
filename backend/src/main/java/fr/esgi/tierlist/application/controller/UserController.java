package fr.esgi.tierlist.application.controller;

import fr.esgi.tierlist.application.dto.UserDto;
import fr.esgi.tierlist.application.form.UserForm;
import fr.esgi.tierlist.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findAll() {
        return userService.findAll().stream().map(UserDto::transfer).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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
